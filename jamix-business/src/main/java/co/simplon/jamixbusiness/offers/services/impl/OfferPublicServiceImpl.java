package co.simplon.jamixbusiness.offers.services.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.commons.EmailSender;
import co.simplon.jamixbusiness.offers.dtos.OfferMessageDto;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.dtos.PortfolioLinkDto;
import co.simplon.jamixbusiness.offers.entities.Offer;
import co.simplon.jamixbusiness.offers.mappers.OfferMapper;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import co.simplon.jamixbusiness.offers.repositories.OfferSpecification;
import co.simplon.jamixbusiness.offers.services.OfferPublicService;
import co.simplon.jamixbusiness.portfolios.repositories.PortfolioRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferPublicServiceImpl implements OfferPublicService {
    private final OfferRepository repository;
    private final PortfolioRepository portfolioRepository;
    private final OfferMapper mapper;
    private final EmailSender sender;
    private final OfferMailTemplate template;

    public OfferPublicServiceImpl(OfferRepository repository, PortfolioRepository portfolioRepository,
	    OfferMapper mapper, EmailSender sender, OfferMailTemplate template) {
	this.repository = repository;
	this.portfolioRepository = portfolioRepository;
	this.mapper = mapper;
	this.sender = sender;
	this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public OfferViewDto getById(Long id) {
	Offer offer = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found with id: " + id));

	PortfolioLinkDto link = portfolioRepository.findByAccount(offer.getAccount()).map(
		portfolio -> new PortfolioLinkDto(portfolio.getId(), portfolio.getImageId(), portfolio.getBandName()))
		.orElse(null);

	return mapper.mapToDto(offer, link);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferViewDto> search(OfferSearchDto criteria) {
	if (hasNoSearchCriteria(criteria)) {
	    return mapper.mapListToDto(repository.findAll());
	}
	Specification<Offer> spec = toSearchSpecification(criteria);
	return repository.findAll(spec).stream().map(mapper::mapToDto).toList();
    }

    private boolean hasNoSearchCriteria(OfferSearchDto dto) {
	return dto.title() == null && dto.instrumentId() == null && dto.styleId() == null && dto.goalId() == null
		&& dto.city() == null && dto.zipCode() == null && dto.postedAfter() == null;
    }

    /**
     * Builds a Specification to filter offers based on the given search criteria.
     *
     * @param dto the criteria to apply (e.g. title, style, goal, etc.)
     * @return a Specification for querying offers
     */
    private Specification<Offer> toSearchSpecification(OfferSearchDto dto) {
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
	if (dto.city() != null && !dto.city().isBlank()) {
	    spec = spec.and(OfferSpecification.hasCity(dto.city()));
	}
	if (dto.zipCode() != null && !dto.zipCode().isBlank()) {
	    spec = spec.and(OfferSpecification.hasZipCode(dto.zipCode()));
	}

	return spec;
    }

    @Override
    public void sendMail(Long offerId, OfferMessageDto messageDto) {
	var offer = repository.findById(offerId)
		.orElseThrow(() -> new EntityNotFoundException("Offer not found: " + offerId));

	var subject = "Name: " + offer.getTitle();
	var body = String.format("From: %s (%s)<br>%s", messageDto.visitorName(), messageDto.visitorMail(),
		messageDto.visitorMessage());

	try {
	    sender.sendEmail(offer.getContactMail(), subject, body);
	} catch (MessagingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public List<OfferViewDto> searchLatest3() {
	return null;
    }

}
