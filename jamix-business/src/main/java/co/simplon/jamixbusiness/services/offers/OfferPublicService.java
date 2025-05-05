package co.simplon.jamixbusiness.services.offers;

import java.util.List;

import co.simplon.jamixbusiness.dtos.OfferViewDto;

/**
 * Public API for browsing music offers. All methods here are accessible without
 * authentication.
 */
public interface OfferPublicService {

    List<OfferViewDto> listAll();

    OfferViewDto getById(Long id);

    List<OfferViewDto> search(String keyword);
}
