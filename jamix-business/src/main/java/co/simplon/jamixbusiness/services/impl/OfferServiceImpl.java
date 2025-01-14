package co.simplon.jamixbusiness.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.entities.preferences.Goal;
import co.simplon.jamixbusiness.entities.preferences.Instrument;
import co.simplon.jamixbusiness.entities.preferences.Style;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.repositories.preferences.GoalRepository;
import co.simplon.jamixbusiness.repositories.preferences.InstrumentRepository;
import co.simplon.jamixbusiness.repositories.preferences.StyleRepository;
import co.simplon.jamixbusiness.services.OfferService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;

    protected OfferServiceImpl(OfferRepository offerRepository, InstrumentRepository instrumentRepository,
	    StyleRepository styleRepository, GoalRepository goalRepository) {
	this.offerRepository = offerRepository;
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
    }

    @Override
    public void create(OfferCreateDto inputs) {
	Instrument instrument = instrumentRepository.findById(inputs.instrumentName())
		.orElseThrow(() -> new IllegalArgumentException("Invalid instrument."));
	Style style = styleRepository.findById(inputs.styleName())
		.orElseThrow(() -> new IllegalArgumentException("Invalid style."));
	Goal goal = goalRepository.findById(inputs.goalName())
		.orElseThrow(() -> new IllegalArgumentException("Invalid goal."));

	Offer offer = new Offer();
	offer.setTitle(inputs.title());
	offer.setDescription(inputs.description());
	offer.setCity(inputs.city());
	offer.setZipCode(inputs.zipCode());
	offer.setMail(inputs.mail());

	offer.setInstrument(instrument);
	offer.setStyle(style);
	offer.setGoal(goal);

	offerRepository.save(offer);
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
	return goalRepository.findByNameIn(goalNames);
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
	return offerRepository.findByGoal_Name(goalName);
    }

    @Override
    public Offer update(OfferUpdateDto offerUpdateDto, Long id) {
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
