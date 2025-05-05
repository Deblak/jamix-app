package co.simplon.jamixbusiness.mappers;

import java.util.List;
import java.util.stream.Collectors;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.dtos.OfferViewDto;
import co.simplon.jamixbusiness.entities.Offer;

/**
 * Utility class to map Offer entity → DTO.
 */
public final class OfferMapper {

//    private OfferMapper() {
//	/* static only */ }
//
//    /**
//     * Map one Offer to OfferViewDto, sans toucher aux associations non chargees.
//     */
//    public static OfferViewDto toViewDto(Offer o) {
//	return new OfferViewDto(o.getId(), o.getTitle(), o.getCity(), o.getZipCode(),
//		o.getImageId() != null ? "/images/" + o.getImageId() : null, o.getContactMail(),
//		o.getInstrument().getId(), o.getStyle().getId(), o.getGoal().getId(), o.getDescription(),
//		o.getCreatedAt());
//    }
//
//    /**
//     * Map a list of Offer → List<OfferViewDto>.
//     */
//    public static List<OfferViewDto> toViewDtoList(List<Offer> offers) {
//	return offers.stream().map(OfferMapper::toViewDto).collect(Collectors.toList());
//    }
    private OfferMapper() {
	// utilitaire statique
    }

    /**
     * Cree une nouvelle entite Offer depuis le DTO de creation.
     */
    public static Offer fromCreateDto(OfferCreateDto dto) {
	Offer offer = new Offer();
	offer.setTitle(dto.title());
	offer.setDescription(dto.description());
	offer.setCity(dto.city());
	offer.setZipCode(dto.zipCode());
	offer.setContactMail(dto.contactMail());
	return offer;
    }

    /**
     * Applique partiellement les champs non nuls du DTO de mise a jour sur
     * l'entite.
     */
    public static void applyUpdateDto(OfferUpdateDto dto, Offer offer) {
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

    /**
     * Transforme lentite en DTO de vue.
     */
    public static OfferViewDto toViewDto(Offer o) {
	return new OfferViewDto(o.getId(), o.getTitle(), o.getCity(), o.getZipCode(),
		o.getImageId() != null ? "/images/" + o.getImageId() : null, o.getContactMail(),
		o.getInstrument().getId(), o.getStyle().getId(), o.getGoal().getId(), o.getDescription(),
		o.getCreatedAt());
    }

    /**
     * Transforme une liste d'entites en liste de DTO de vue.
     */
    public static List<OfferViewDto> toViewDtoList(List<Offer> offers) {
	return offers.stream().map(OfferMapper::toViewDto).collect(Collectors.toList());
    }
}
