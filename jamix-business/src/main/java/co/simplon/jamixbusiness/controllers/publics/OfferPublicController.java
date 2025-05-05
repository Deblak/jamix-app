package co.simplon.jamixbusiness.controllers.publics;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.dtos.OfferViewDto;
import co.simplon.jamixbusiness.services.offers.OfferPublicService;

@RestController
@RequestMapping("/offers")
public class OfferPublicController {

    private final OfferPublicService service;

    public OfferPublicController(OfferPublicService service) {
	this.service = service;
    }

    @GetMapping
    public List<OfferViewDto> listAll() {
	return service.listAll();
    }

    @GetMapping("/{id}")
    public OfferViewDto getById(@PathVariable Long id) {
	return service.getById(id);
    }

    @GetMapping("/search")
    public List<OfferViewDto> search(@RequestParam String keyword) {
	return service.search(keyword);
    }
}
