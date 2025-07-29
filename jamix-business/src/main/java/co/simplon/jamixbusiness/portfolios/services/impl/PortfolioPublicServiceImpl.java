package co.simplon.jamixbusiness.portfolios.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.offers.entities.Offer;
import co.simplon.jamixbusiness.offers.mappers.OfferMapper;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import co.simplon.jamixbusiness.portfolios.dtos.OfferLinkDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioFullDto;
import co.simplon.jamixbusiness.portfolios.entities.Portfolio;
import co.simplon.jamixbusiness.portfolios.mappers.PortfolioMapper;
import co.simplon.jamixbusiness.portfolios.repositories.PortfolioRepository;
import co.simplon.jamixbusiness.portfolios.services.PortfolioPublicService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class PortfolioPublicServiceImpl implements PortfolioPublicService {
    private final PortfolioRepository repository;
    private final PortfolioMapper mapper;
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public PortfolioPublicServiceImpl(PortfolioRepository repository, PortfolioMapper mapper,
	    OfferRepository offerRepository, OfferMapper offerMapper) {
	this.repository = repository;
	this.mapper = mapper;
	this.offerRepository = offerRepository;
	this.offerMapper = offerMapper;
    }

    // cross dependency -> refacto to : Facade pattern ?
    @Override
    public PortfolioFullDto getById(Long id) {
	Portfolio portfolio = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "portfolio not found"));
	PortfolioDto portfolioDto = mapper.mapToDto(portfolio);
	List<OfferLinkDto> offers = offerRepository.findByAccount(portfolio.getAccount()).stream()
		.map(offerMapper::toLinkDto).toList();
	return new PortfolioFullDto(portfolioDto, offers);
    }

    @Override
    public List<PortfolioDto> searchByBandName(String bandName) {
	if (bandName == null || bandName.isBlank()) {
	    throw new IllegalArgumentException("bandName is mandatory");
	}
	return repository.findByBandNameContainingIgnoreCase(bandName.trim()).stream().map(mapper::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortfolioDto> getAll() {
	return mapper.mapListToDto(repository.findAll());
    }

    @Override
    public Optional<PortfolioDto> getByOfferId(Long offerId) {
	Offer offer = offerRepository.findById(offerId)
		.orElseThrow(() -> new EntityNotFoundException("Offer id=" + offerId + " has not found"));

	return repository.findByAccount(offer.getAccount()).map(mapper::mapToDto);
    }

}
