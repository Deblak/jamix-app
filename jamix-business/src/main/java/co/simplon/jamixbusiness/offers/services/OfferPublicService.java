package co.simplon.jamixbusiness.offers.services;

import java.util.List;

import co.simplon.jamixbusiness.offers.dtos.ContactMusician;
import co.simplon.jamixbusiness.offers.dtos.OfferFullDto;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;

/**
 * Public API for browsing music offers. All methods are accessible without
 * authentication.
 */
public interface OfferPublicService {

    OfferViewDto getById(Long id);

    List<OfferViewDto> search(OfferSearchDto criteria);

    void sendMail(ContactMusician form);

    OfferFullDto getFullById(Long id);
}
