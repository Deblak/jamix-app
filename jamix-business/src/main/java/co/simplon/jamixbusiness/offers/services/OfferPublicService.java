package co.simplon.jamixbusiness.offers.services;

import java.util.List;

import co.simplon.jamixbusiness.offers.dtos.OfferMessageDto;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;

/**
 * Public API for browsing music offers. All methods are accessible without
 * authentication.
 */
public interface OfferPublicService {

    OfferViewDto getById(Long id);

    List<OfferViewDto> search(OfferSearchDto criteria);

    void sendMail(Long offerId, OfferMessageDto messageDto);

}
