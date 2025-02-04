package co.simplon.jamixbusiness.services.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.dtos.OfferViewDto;
import co.simplon.jamixbusiness.entities.Account;
import co.simplon.jamixbusiness.entities.Goal;
import co.simplon.jamixbusiness.entities.Instrument;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.entities.Style;
import co.simplon.jamixbusiness.repositories.AccountRepository;
import co.simplon.jamixbusiness.repositories.GoalRepository;
import co.simplon.jamixbusiness.repositories.InstrumentRepository;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.repositories.StyleRepository;
import co.simplon.jamixbusiness.services.OfferService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;
    private final AccountRepository accountRepository;
    private final AccountServiceImpl accountService;

    protected OfferServiceImpl(OfferRepository offerRepository, InstrumentRepository instrumentRepository,
	    StyleRepository styleRepository, GoalRepository goalRepository, AccountRepository accountRepository,
	    AccountServiceImpl accountService) {
	this.offerRepository = offerRepository;
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
	this.accountRepository = accountRepository;
	this.accountService = accountService;
    }

    @Value("${jamix.uploads.destination}")
    private String uploadsDest;

    @Value("${jamix.uploads.url}")
    private String imageUrl;

    @Override
    @Transactional
    public void create(OfferCreateDto inputs) {
	Offer offer = new Offer();

	offer.setTitle(inputs.title());
	offer.setDescription(inputs.description());
	offer.setCity(inputs.city());
	offer.setZipCode(inputs.zipCode());
	offer.setContactMail(inputs.contactMail());

	Instrument instrument = instrumentRepository.findById(inputs.instrumentId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid instrument."));
	Style style = styleRepository.findById(inputs.styleId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid style."));
	Goal goal = goalRepository.findById(inputs.goalId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid goal."));

	offer.setInstrument(instrument);
	offer.setStyle(style);
	offer.setGoal(goal);

	MultipartFile image = inputs.image();
	if (image != null) {
	    String imageId = buildImageId(image);
	    storeImage(image, imageId);
	    offer.setImageId(imageId);
	}
	Account account = accountService.getAuthenticatedAccount()
		.orElseThrow(() -> new RuntimeException("Invalid auth"));
	offer.setAccount(account);

	offerRepository.save(offer);
    }

    private String buildImageId(MultipartFile image) {
	UUID uuid = UUID.randomUUID();
	String name = image.getOriginalFilename();
	int index = name.lastIndexOf('.');
	String ext = name.substring(index, name.length());
	return uuid + ext;
    }

    private void storeImage(MultipartFile image, String imageId) {
	try {
	    String dest = String.format("%s/%s", uploadsDest, imageId);
	    File file = new File(dest);
	    image.transferTo(file);
	} catch (Exception ex) {
	    throw new RuntimeException(ex);
	}
    }

    public String buildImageUrl(String imageId) {
	// return imageUrl + "/images/" + imageId;
	return String.format("%s/%s", imageUrl, imageId);

    }

    private void addImageUrlToOffer(Offer offer) {
	// String imageUrl = buildImageUrl(offer.getImageId());
	// offer.setImageId(imageUrl);
	offer.setImageId(buildImageUrl(offer.getImageId()));

    }

    public OfferViewDto toDto(Offer offer) {
	String imageUrl = offer.getImageId() != null ? buildImageUrl(offer.getImageId()) : null;
	return new OfferViewDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getCity(),
		offer.getZipCode(), offer.getContactMail(), null, null, null, imageUrl, null);
    }

    /*
     * @Override public Collection<OfferViewDto> getAll() { return
     * offerRepository.findAllProjectedBy(); }
     */
    @Override
    public List<Offer> getAll() {
	List<Offer> offers = offerRepository.findAll();
	offers.forEach(this::addImageUrlToOffer);
	return offers;
    }

    @Override
    public Offer getById(Long id) {
	Offer offer = offerRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
	addImageUrlToOffer(offer);
	return offer;
    }

    @Transactional(readOnly = true)
    public Set<Offer> getOffersByAuthenticatedAccount() {
	Account account = accountService.getAuthenticatedAccount()
		.orElseThrow(() -> new RuntimeException("User not authenticated"));
	Set<Offer> offers = offerRepository.findByAccount(account);

	return offerRepository.findByAccount(account);
    }

    @Override
    public List<Offer> findByTitle(String keyword) {
	return offerRepository.findByTitleContaining(keyword);
    }

    private List<Instrument> getInstrumentsByName(List<String> instrumentNames) {
	return instrumentRepository.findByNameIn(instrumentNames);
    }

    private List<Style> getStylesByName(List<String> styleNames) {
	return styleRepository.findByNameIn(styleNames);
    }

    private List<Goal> getGoalsByName(List<String> goalNames) {
	return goalRepository.findByTypeIn(goalNames);
    }

    @Override
    public List<Offer> findByInstrument(String instrumentName) {
	return offerRepository.findByInstrument_Name(instrumentName);
    }

    @Override
    public List<Offer> findByStyle(String styleName) {
	return offerRepository.findByStyle_Name(styleName);
    }

    @Override
    public List<Offer> findByGoal(String goalName) {
	return offerRepository.findByGoal_Type(goalName);
    }

    @Override
    public Offer update(OfferUpdateDto offerUpdateDto, Long id, MultipartFile imageFile) {
	Optional<Offer> optional = offerRepository.findById(id);

	if (optional.isPresent()) {
	    Offer offerUpdate = optional.get(); // verify

	    if (offerUpdateDto.title() != null) {
		offerUpdate.setTitle(offerUpdateDto.title());
	    }
	    if (offerUpdateDto.description() != null) {
		offerUpdate.setDescription(offerUpdateDto.description());
	    }
	    return offerRepository.save(offerUpdate);
	}
	throw new EntityNotFoundException("Offer not found with id :" + id);
	/*
	 * Offer existingOffer = offerRepository.findById(id) .orElseThrow(() -> new
	 * EntityNotFoundException("Offer not found"));
	 * existingOffer.setTitle(offerUpdateDto.title());
	 * existingOffer.setDescription(offerUpdateDto.description());
	 * existingOffer.setCity(offerUpdateDto.city());
	 * existingOffer.setZipCode(offerUpdateDto.zipCode());
	 * existingOffer.setMail(offerUpdateDto.mail());
	 * existingOffer.setInstrument(offerUpdateDto.instrumentId());;
	 * existingOffer.setStyle(offerUpdateDto.styleId());
	 * existingOffer.setGoal(offerUpdateDto.goalId());
	 *
	 * // Handle image if (imageFile != null && !imageFile.isEmpty()) { try { Image
	 * image = imageService.saveImage(imageFile.getContentType(),
	 * imageFile.getBytes()); existingOffer.setImage(image); } catch (IOException e)
	 * { throw new RuntimeException("Failed to update image", e); } }
	 *
	 * return offerRepository.save(existingOffer);
	 */
    }

    @Override
    public boolean delete(Long id) {
	if (offerRepository.findById(id).isPresent()) {
	    offerRepository.deleteById(id);
	    return true;
	}
	throw new EntityNotFoundException("Offer not found with id: " + id);
    }

}
