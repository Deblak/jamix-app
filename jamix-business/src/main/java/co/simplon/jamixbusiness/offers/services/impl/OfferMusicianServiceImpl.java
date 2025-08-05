package co.simplon.jamixbusiness.offers.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.commons.images.ImageService;
import co.simplon.jamixbusiness.locations.Location;
import co.simplon.jamixbusiness.locations.LocationCreateDto;
import co.simplon.jamixbusiness.locations.LocationRepository;
import co.simplon.jamixbusiness.locations.LocationService;
import co.simplon.jamixbusiness.locations.LocationViewDto;
import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.entities.Offer;
import co.simplon.jamixbusiness.offers.mappers.OfferMapper;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import co.simplon.jamixbusiness.offers.services.OfferMusicianService;
import co.simplon.jamixbusiness.preferences.Goal;
import co.simplon.jamixbusiness.preferences.GoalRepository;
import co.simplon.jamixbusiness.preferences.Instrument;
import co.simplon.jamixbusiness.preferences.InstrumentRepository;
import co.simplon.jamixbusiness.preferences.Style;
import co.simplon.jamixbusiness.preferences.StyleRepository;
import co.simplon.jamixbusiness.security.CurrentUserManager;

@Service
public class OfferMusicianServiceImpl implements OfferMusicianService {

    private final OfferRepository repository;
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;
    private final CurrentUserManager currentUserManager;
    private final ImageService imageService;
    private final OfferMapper mapper;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    public OfferMusicianServiceImpl(OfferRepository repository, InstrumentRepository instrumentRepository,
	    StyleRepository styleRepository, GoalRepository goalRepository, CurrentUserManager currentUserManager,
	    ImageService imageService, OfferMapper mapper, LocationRepository locationRepository,
	    LocationService locationService) {
	this.repository = repository;
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
	this.currentUserManager = currentUserManager;
	this.imageService = imageService;
	this.mapper = mapper;
	this.locationRepository = locationRepository;
	this.locationService = locationService;
    }

    @Override
    @Transactional
    public OfferViewDto create(OfferCreateDto dto, MultipartFile image) {
	Offer offer = mapper.createEntityFromDto(dto);

	Instrument instrument = instrumentRepository.findById(dto.instrumentId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid instrument ID"));
	Style style = styleRepository.findById(dto.styleId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid style ID"));
	Goal goal = goalRepository.findById(dto.goalId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid goal ID"));
	offer.setInstrument(instrument);
	offer.setStyle(style);
	offer.setGoal(goal);
	String city = dto.city().trim();
	String zipCode = dto.zipCode().trim();
	LocationCreateDto locationCreateDto = new LocationCreateDto(city, zipCode);
	LocationViewDto locationDto = locationService.create(locationCreateDto);
	Location location = locationRepository.findById(locationDto.id())
		.orElseThrow(() -> new IllegalStateException("Location created but not found"));
	offer.setLocation(location);
	// Optional image handling
	if (image != null && !image.isEmpty()) {
	    String imageId = imageService.store(image);
	    offer.setImageId(imageId);
	}
	Account account = currentUserManager.getCurrentAccount();
	offer.setAccount(account);
	Offer saved = repository.save(offer);
	return mapper.mapToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> getOwnedOffers() {
	Account account = currentUserManager.getCurrentAccount();
	List<Offer> offers = repository.findByAccount(account);
	return mapper.mapListToDto(offers);
    }

    @Override
    @Transactional
    public OfferViewDto update(Long id, OfferUpdateDto dto) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id " + id));

	Account account = currentUserManager.getCurrentAccount();
	if (!offer.getAccount().getEmail().equalsIgnoreCase(account.getEmail())) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not own this offer");
	}

	mapper.patchEntityFromDto(dto, offer);

	if (dto.city() != null && dto.zipCode() != null) {
	    String city = dto.city().trim();
	    String zipCode = dto.zipCode().trim();

	    LocationViewDto locationDto = new LocationViewDto(null, city, zipCode);
	    if (!locationService.isReal(locationDto)) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City or zip code are not valid");
	    }

	    Optional<Location> existingLocation = locationRepository.findByCityIgnoreCaseAndZipCode(city, zipCode);
	    Location location = existingLocation.orElseGet(() -> {
		LocationCreateDto createDto = new LocationCreateDto(city, zipCode);
		return locationRepository.findById(locationService.create(createDto).id())
			.orElseThrow(() -> new IllegalStateException("Location just created but not found"));
	    });

	    offer.setLocation(location);
	}
	if (dto.instrumentId() != null) {
	    Instrument instr = instrumentRepository.findById(dto.instrumentId())
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid instrument ID"));
	    offer.setInstrument(instr);
	}
	if (dto.styleId() != null) {
	    Style s = styleRepository.findById(dto.styleId())
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid style ID"));
	    offer.setStyle(s);
	}
	if (dto.goalId() != null) {
	    Goal g = goalRepository.findById(dto.goalId())
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid goal ID"));
	    offer.setGoal(g);
	}

	MultipartFile image = dto.image();
	if (image != null && !image.isEmpty()) {
	    if (offer.getImageId() != null) {
		imageService.delete(offer.getImageId());
	    }
	    String imageId = imageService.store(image);
	    offer.setImageId(imageId);
	}

	Offer saved = repository.save(offer);
	return mapper.mapToDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id " + id));

	Account account = currentUserManager.getCurrentAccount();

	if (!offer.getAccount().getEmail().equalsIgnoreCase(account.getEmail())) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not own this offer");
	}

	if (offer.getImageId() != null) {
	    imageService.delete(offer.getImageId());
	}

	repository.delete(offer);
    }

    @Override
    @Transactional
    public OfferViewDto uploadImage(Long id, MultipartFile image) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id " + id));

	Account account = currentUserManager.getCurrentAccount();
	if (!offer.getAccount().equals(account)) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot update another user’s offer");
	}

	String imageId = imageService.store(image);
	offer.setImageId(imageId);

	Offer saved = repository.save(offer);
	return mapper.mapToDto(saved);
    }
}
