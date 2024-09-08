package co.simplon.jamixbusiness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.entities.Ad;
import co.simplon.jamixbusiness.services.AdService;

@RequestMapping("/ads")
@RestController
public class AdController {

    private final AdService service;

    public AdController(AdService service) {
	super();
	this.service = service;
    }

    @GetMapping
    public List<Ad> getAllAds() {
	return service.getAllAds();
    }
}
