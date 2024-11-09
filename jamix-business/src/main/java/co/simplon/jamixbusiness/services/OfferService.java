package co.simplon.jamixbusiness.services;

import java.util.List;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;

public interface OfferService {
    void create(OfferCreateDto inputs);

    List<Offer> findAllOffers();

    Offer updateOffer(OfferUpdateDto offerUpdateDto, Long id);

    boolean deleteOffer(Long id);

}
