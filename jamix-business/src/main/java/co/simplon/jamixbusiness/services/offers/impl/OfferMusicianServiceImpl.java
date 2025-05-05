package co.simplon.jamixbusiness.services.offers.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.dtos.OfferViewDto;
import co.simplon.jamixbusiness.entities.Account;
import co.simplon.jamixbusiness.entities.Goal;
import co.simplon.jamixbusiness.entities.Instrument;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.entities.Style;
import co.simplon.jamixbusiness.mappers.OfferMapper;
import co.simplon.jamixbusiness.repositories.GoalRepository;
import co.simplon.jamixbusiness.repositories.InstrumentRepository;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.repositories.StyleRepository;
import co.simplon.jamixbusiness.security.CurrentUserManager;
import co.simplon.jamixbusiness.services.images.ImageService;
import co.simplon.jamixbusiness.services.offers.OfferMusicianService;

@Service
public class OfferMusicianServiceImpl implements OfferMusicianService {

    private final OfferRepository repository;
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;
    private final CurrentUserManager currentUserManager;
    private final ImageService imageService;

    public OfferMusicianServiceImpl(OfferRepository repository, InstrumentRepository instrumentRepository,
	    StyleRepository styleRepository, GoalRepository goalRepository, CurrentUserManager currentUserManager,
	    ImageService imageService) {
	this.repository = repository;
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
	this.currentUserManager = currentUserManager;
	this.imageService = imageService;
    }

    @Override
    @Transactional
    public OfferViewDto create(OfferCreateDto dto, MultipartFile image) {
	Offer offer = OfferMapper.fromCreateDto(dto);

	Instrument instrument = instrumentRepository.findById(dto.instrumentId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid instrument ID"));
	Style style = styleRepository.findById(dto.styleId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid style ID"));
	Goal goal = goalRepository.findById(dto.goalId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid goal ID"));

	offer.setInstrument(instrument);
	offer.setStyle(style);
	offer.setGoal(goal);

	// Optional image handling
	if (image != null && !image.isEmpty()) {
	    String imageId = imageService.store(image);
	    offer.setImageId(imageId);
	}

	Account account = currentUserManager.getCurrentAccount();
	offer.setAccount(account);

	Offer saved = repository.save(offer);
	return OfferMapper.toViewDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> listMine() {
	Account account = currentUserManager.getCurrentAccount();
	List<Offer> offers = repository.findByAccount(account);
	return OfferMapper.toViewDtoList(offers);
    }

    @Override
    @Transactional
    public OfferViewDto update(Long id, OfferUpdateDto dto, MultipartFile image) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id " + id));

	Account account = currentUserManager.getCurrentAccount();
	if (!offer.getAccount().equals(account)) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not own this offer");
	}

	OfferMapper.applyUpdateDto(dto, offer);

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

	if (image != null && !image.isEmpty()) {
	    if (offer.getImageId() != null) {
		imageService.delete(offer.getImageId());
	    }
	    String imageId = imageService.store(image);
	    offer.setImageId(imageId);
	}

	Offer saved = repository.save(offer);
	return OfferMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id " + id));

	Account account = currentUserManager.getCurrentAccount();
	if (!offer.getAccount().equals(account)) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not own this offer");
	}

	if (offer.getImageId() != null) {
	    imageService.delete(offer.getImageId());
	}

	repository.delete(offer);
    }

    @Override
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
	return OfferMapper.toViewDto(saved);
    }
}
