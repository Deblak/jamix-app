package co.simplon.jamixbusiness.services;

import java.util.List;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;

public interface OfferService {
    void create(OfferCreateDto inputs);

    List<Offer> getAll();

    Offer getById(Long id);

    List<Offer> findByTitle(String keyword);

    List<Offer> findByInstrument(String instrumentName);

    List<Offer> findByStyle(String styleName);

    List<Offer> findByGoal(String goalType);

    Offer update(OfferUpdateDto offerUpdateDto, Long id);

    boolean delete(Long id);

}
