package co.simplon.jamixbusiness.offers.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;

/**
 * API for authenticated Musicians to manage their own offers. Secured: only
 * users with role MUSICIAN may call these methods.
 */
public interface OfferMusicianService {

    OfferViewDto create(OfferCreateDto dto, MultipartFile image);

    List<OfferViewDto> getOwnedOffers();

    OfferViewDto update(Long id, OfferUpdateDto dto);

    void delete(Long id);

    OfferViewDto uploadImage(Long id, MultipartFile image);
}
