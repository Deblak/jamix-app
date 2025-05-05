package co.simplon.jamixbusiness.services.offers.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.dtos.OfferViewDto;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.mappers.OfferMapper;
import co.simplon.jamixbusiness.repositories.OfferRepository;
import co.simplon.jamixbusiness.services.offers.OfferPublicService;

@Service
public class OfferPublicServiceImpl implements OfferPublicService {

    private final OfferRepository repository;

    public OfferPublicServiceImpl(OfferRepository repository) {
	this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> listAll() {
	List<Offer> offers = repository.findAll();
	return OfferMapper.toViewDtoList(offers);
    }

    @Override
    @Transactional(readOnly = true)
    public OfferViewDto getById(Long id) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id: " + id));
	return OfferMapper.toViewDto(offer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> search(String keyword) {
	List<Offer> offers = repository.findByTitleContainingIgnoreCase(keyword);
	return OfferMapper.toViewDtoList(offers);
    }
}
