package co.simplon.jamixbusiness.offers.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.dtos.PortfolioLinkDto;
import co.simplon.jamixbusiness.offers.entities.Offer;
import co.simplon.jamixbusiness.portfolios.dtos.OfferLinkDto;

/**
 * Utility class to map Offer entity to DTO
 */
@Component
public final class OfferMapper {

    public Offer createEntityFromDto(OfferCreateDto dto) {
	Offer offer = new Offer();
	offer.setTitle(dto.title());
	offer.setDescription(dto.description());
	offer.setContactMail(dto.contactMail());
	return offer;
    }

    public void patchEntityFromDto(OfferUpdateDto dto, Offer offer) {
	if (dto.title() != null) {
	    offer.setTitle(dto.title());
	}
	if (dto.description() != null) {
	    offer.setDescription(dto.description());
	}
	if (dto.contactMail() != null) {
	    offer.setContactMail(dto.contactMail());
	}
    }

    public OfferViewDto mapToDto(Offer offer, PortfolioLinkDto portfolioLink) {
	return new OfferViewDto(offer.getId(), offer.getTitle(), offer.getImageId(), offer.getContactMail(),
		offer.getLocation().getId(), offer.getLocation().getCity(), offer.getLocation().getZipCode(),
		offer.getInstrument().getId(), offer.getInstrument().getName(), offer.getStyle().getId(),
		offer.getStyle().getName(), offer.getGoal().getId(), offer.getGoal().getType(), offer.getDescription(),
		offer.getCreatedAt(), portfolioLink);
    }

    public OfferLinkDto toLinkDto(Offer offer) {
	return new OfferLinkDto(offer.getId(), offer.getTitle(), offer.getImageId(), offer.getLocation().getCity(),
		offer.getLocation().getZipCode(), offer.getInstrument().getName(), offer.getStyle().getName(),
		offer.getGoal().getType(), offer.getCreatedAt());
    }

    public OfferViewDto mapToDto(Offer offer) {
	return mapToDto(offer, null);
    }

    public List<OfferViewDto> mapListToDto(List<Offer> offers) {
	return offers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
