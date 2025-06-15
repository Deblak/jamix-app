package co.simplon.jamixbusiness.offers.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.simplon.jamixbusiness.offers.Offer;
import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;

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

    public OfferViewDto mapToDto(Offer o) {
	return new OfferViewDto(o.getId(), o.getTitle(), o.getImageId(), o.getContactMail(), o.getLocation().getId(),
		o.getLocation().getCity(), o.getLocation().getZipCode(), o.getInstrument().getId(),
		o.getInstrument().getName(), o.getStyle().getId(), o.getStyle().getName(), o.getGoal().getId(),
		o.getGoal().getType(), o.getDescription(), o.getCreatedAt());
    }

    public List<OfferViewDto> mapListToDto(List<Offer> offers) {
	return offers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
