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
	offer.setCity(dto.city());
	offer.setZipCode(dto.zipCode());
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
	if (dto.city() != null) {
	    offer.setCity(dto.city());
	}
	if (dto.zipCode() != null) {
	    offer.setZipCode(dto.zipCode());
	}
	if (dto.contactMail() != null) {
	    offer.setContactMail(dto.contactMail());
	}
    }

    public OfferViewDto mapToDto(Offer o) {
	return new OfferViewDto(o.getId(), o.getTitle(), o.getCity(), o.getZipCode(),
		o.getImageId() != null ? "/images/" + o.getImageId() : null, o.getContactMail(),
		o.getInstrument().getId(), o.getStyle().getId(), o.getGoal().getId(), o.getDescription(),
		o.getCreatedAt());
    }

    public List<OfferViewDto> mapListToDto(List<Offer> offers) {
	return offers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

//    public static OfferReadDto toReadDto(Offer offer) {
//	return new OfferReadDto(offer.getTitle(), offer.getCity(), offer.getZipCode(), offer.getImageUrl(),
//		offer.getContactMail(), offer.getInstrument().getName(), offer.getStyle().getName(),
//		offer.getGoal().getType(), offer.getDescription(), offer.getCreatedAt());
//    }
}
