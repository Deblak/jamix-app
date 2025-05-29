package co.simplon.jamixbusiness.offers.services;

import java.util.List;

import co.simplon.jamixbusiness.offers.dtos.OfferReadDto;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;

public interface OfferSearchService {

    // Page searchOffers(OfferSearchDto criteria);
    List<OfferViewDto> findOffers(OfferSearchDto criteria);

    List<OfferReadDto> search();

}
