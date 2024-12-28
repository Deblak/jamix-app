package co.simplon.jamixbusiness.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.services.OfferService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
	this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers() {
	return offerRepository.findAll();
    }

    @Override
    public void create(OfferCreateDto inputs) {
	Offer offer = new Offer();
	offer.setTitle(inputs.title());
	offer.setDescription(inputs.description());
	offer.setCity(inputs.city());
	offer.setZipCode(inputs.zipCode());
	offer.setMail(inputs.mail());
	offerRepository.save(offer);

    }

//    @Override
//    public List<Offer> findAllOffers() {
//	// TODO Auto-generated method stub
//	return offerRepository.findAll();
//    }

    @Override
    public Offer updateOffer(OfferUpdateDto offerUpdateDto, Long id) {
	Optional<Offer> optional = offerRepository.findById(id);
	if (optional.isPresent()) {
	    Offer offerUpdate = optional.get();

	    if (offerUpdateDto.title() != null) {
		offerUpdate.setTitle(offerUpdateDto.title());
	    }
	    if (offerUpdateDto.description() != null) {
		offerUpdate.setDescription(offerUpdateDto.description());
	    }

	    // offerRepository.save(offerUpdate);
	    return offerRepository.save(offerUpdate);
	}
	throw new EntityNotFoundException("Offer not found with id :" + id);
    }

    @Override
    public boolean deleteOffer(Long id) {
	if (offerRepository.findById(id).isPresent()) {
	    offerRepository.deleteById(id);
	    return true;
	}
	throw new EntityNotFoundException("Offer not found with id: " + id);
	// return false;
    }

    @Override
    public Offer findOffer(Long id) {
	Optional<Offer> optional = offerRepository.findById(id);
	return optional.orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
    }

}
