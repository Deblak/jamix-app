package co.simplon.jamixbusiness.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.dtos.AdCreateDto;
import co.simplon.jamixbusiness.dtos.AdUpdateDto;
import co.simplon.jamixbusiness.entities.Ad;
import co.simplon.jamixbusiness.repositories.AdRepository;
import co.simplon.jamixbusiness.services.AdService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
	super();
	this.adRepository = adRepository;
    }

    public List<Ad> getAllAds() {
	return adRepository.findAll();
    }

    @Override
    public void create(AdCreateDto inputs) {
	Ad ad = new Ad();
	ad.setTitle(inputs.title());
	ad.setDescription(inputs.description());
	adRepository.save(ad);

    }

    @Override
    public List<Ad> findAllAds() {
	// TODO Auto-generated method stub
	return adRepository.findAll();
    }

    @Override
    public Ad updateAd(AdUpdateDto adUpdateDto, Long id) {
	Optional<Ad> optional = adRepository.findById(id);
	if (optional.isPresent()) {
	    Ad adUpdate = optional.get();

	    if (adUpdateDto.title() != null) {
		adUpdate.setTitle(adUpdateDto.title());
	    }
	    if (adUpdateDto.description() != null) {
		adUpdate.setDescription(adUpdateDto.description());
	    }

	    adRepository.save(adUpdate);
	    return adUpdate;
	}
	throw new EntityNotFoundException("Ad not found with id :" + id);
    }

    @Override
    public boolean deleteAd(Long id) {
	if (adRepository.findById(id).isPresent()) {
	    adRepository.deleteById(id);
	    return true;
	}
	return false;
    }

}
