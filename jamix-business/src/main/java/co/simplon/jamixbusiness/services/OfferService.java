package co.simplon.jamixbusiness.services;

import java.util.List;

import co.simplon.jamixbusiness.dtos.AdCreateDto;
import co.simplon.jamixbusiness.dtos.AdUpdateDto;
import co.simplon.jamixbusiness.entities.Ad;

public interface AdService {
    void create(AdCreateDto inputs);

    List<Ad> findAllAds();

    Ad updateAd(AdUpdateDto adUpdateDto, Long id);

    boolean deleteAd(Long id);

}
