package co.simplon.jamixbusiness.offers.services.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.offers.Offer;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.mappers.OfferMapper;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import co.simplon.jamixbusiness.offers.repositories.OfferSpecification;
import co.simplon.jamixbusiness.offers.services.OfferPublicService;

@Service
public class OfferPublicServiceImpl implements OfferPublicService {
    private final OfferRepository repository;
    private final OfferMapper mapper;

    public OfferPublicServiceImpl(OfferRepository repository, OfferMapper mapper) {
	this.repository = repository;
	this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public OfferViewDto getById(Long id) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id: " + id));
	return mapper.mapToDto(offer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> search(String keyword) {
	return mapper.mapListToDto(repository.findByTitleContainingIgnoreCase(keyword));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> searchOffers(OfferSearchDto criteria) {
	if (hasNoSearchCriteria(criteria)) {
	    return mapper.mapListToDto(repository.findAll());
	}
	Specification<Offer> spec = mapToSpecification(criteria);
	return repository.findAll(spec).stream().map(mapper::mapToDto).toList();
    }

    private boolean hasNoSearchCriteria(OfferSearchDto dto) {
	return dto.title() == null && dto.instrumentId() == null && dto.styleId() == null && dto.goalId() == null
		&& dto.postedAfter() == null;
    }

    private Specification<Offer> mapToSpecification(OfferSearchDto dto) {
	Specification<Offer> spec = Specification.where(null);

	if (dto.title() != null && !dto.title().isBlank()) {
	    spec = spec.and(OfferSpecification.titleContains(dto.title()));
	}
	if (dto.instrumentId() != null) {
	    spec = spec.and(OfferSpecification.hasInstrument(dto.instrumentId()));
	}
	if (dto.styleId() != null) {
	    spec = spec.and(OfferSpecification.hasStyle(dto.styleId()));
	}
	if (dto.goalId() != null) {
	    spec = spec.and(OfferSpecification.hasGoal(dto.goalId()));
	}
	if (dto.postedAfter() != null) {
	    spec = spec.and(OfferSpecification.postedAfter(dto.postedAfter()));
	}

	return spec;
    }

}
