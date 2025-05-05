package co.simplon.jamixbusiness.services.offers;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.dtos.OfferViewDto;

/**
 * API for authenticated Musicians to manage their own offers. Secured: only
 * users with role MUSICIAN may call these methods.
 */
public interface OfferMusicianService {

    OfferViewDto create(OfferCreateDto dto, MultipartFile image);

    List<OfferViewDto> listMine();

    OfferViewDto update(Long id, OfferUpdateDto dto, MultipartFile image);

    void delete(Long id);

    OfferViewDto uploadImage(Long id, MultipartFile image);
}
