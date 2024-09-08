package co.simplon.jamixbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.entities.Ad;
import co.simplon.jamixbusiness.repositories.AdRepository;

@Service
public class AdService {

    private final AdRepository ads;

    public AdService(AdRepository ads) {
	super();
	this.ads = ads;
    }

    public List<Ad> getAllAds() {
	return ads.findAll();
    }

}
