package co.simplon.jamixbusiness.services;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;

public interface OfferService {
    void create(OfferCreateDto inputs);

//    List<Offer> findAllOffers();

    Offer findOffer(Long id);

    Offer updateOffer(OfferUpdateDto offerUpdateDto, Long id);

    boolean deleteOffer(Long id);

}
