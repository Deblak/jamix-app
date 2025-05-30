package co.simplon.jamixbusiness.offers.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.services.OfferPublicService;

@RestController
@RequestMapping("/offers")
public class OfferPublicController {

    private final OfferPublicService service;

    public OfferPublicController(OfferPublicService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    public OfferViewDto getById(@PathVariable Long id) {
	return service.getById(id);
    }

    @GetMapping("/search")
    public List<OfferViewDto> search(@RequestParam String keyword) {
	return service.search(keyword);
    }

    @GetMapping
    public List<OfferViewDto> searchOffers(@ModelAttribute OfferSearchDto criteria) {
	return service.searchOffers(criteria);
    }

}
