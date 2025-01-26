package co.simplon.jamixbusiness.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Image;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.entities.preferences.Goal;
import co.simplon.jamixbusiness.entities.preferences.Instrument;
import co.simplon.jamixbusiness.entities.preferences.Style;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.repositories.preferences.GoalRepository;
import co.simplon.jamixbusiness.repositories.preferences.InstrumentRepository;
import co.simplon.jamixbusiness.repositories.preferences.StyleRepository;
import co.simplon.jamixbusiness.services.ImageService;
import co.simplon.jamixbusiness.services.OfferService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;

    private final ImageService imageService;

    protected OfferServiceImpl(OfferRepository offerRepository, InstrumentRepository instrumentRepository,
	    StyleRepository styleRepository, GoalRepository goalRepository, ImageService imageService) {
	this.offerRepository = offerRepository;
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
	this.imageService = imageService;
    }

    @Override
    @Transactional
    public Offer create(OfferCreateDto inputs, MultipartFile imageFile) {
	Instrument instrument = instrumentRepository.findById(inputs.instrumentId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid instrument."));
	Style style = styleRepository.findById(inputs.styleId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid style."));
	Goal goal = goalRepository.findById(inputs.goalId())
		.orElseThrow(() -> new IllegalArgumentException("Invalid goal."));

	Image image = null;
	if (imageFile != null && !imageFile.isEmpty()) {
	    image = imageService.saveImage(imageFile);
	}

	Offer offer = new Offer();
	offer.setTitle(inputs.title());
	offer.setDescription(inputs.description());
	offer.setCity(inputs.city());
	offer.setZipCode(inputs.zipCode());
	offer.setMail(inputs.mail());

	offer.setInstrument(instrument);
	offer.setStyle(style);
	offer.setGoal(goal);

	offer.setImage(image);

	return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getAll() {
	return offerRepository.findAll();
    }

    @Override
    public Offer getById(Long id) {
	Optional<Offer> optional = offerRepository.findById(id);
	return optional.orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
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
	    Offer offerUpdate = optional.get();

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
