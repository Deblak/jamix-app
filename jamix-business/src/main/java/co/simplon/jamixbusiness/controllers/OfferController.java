package co.simplon.jamixbusiness.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.entities.Offer;
import co.simplon.jamixbusiness.services.impl.OfferServiceImpl;
import jakarta.validation.Valid;

@RequestMapping("/offers")
@RestController
public class OfferController {

    private final OfferServiceImpl service;

    public OfferController(OfferServiceImpl service) {
	super();
	this.service = service;
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody OfferCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping
    public List<Offer> getAllOffers() {
	return service.getAllOffers();
    }

    /**
     * futur /{userId}/offers
     *
     * @return
     */
    @GetMapping("/my-offer")
    public List<Offer> getMyOffers() {
	return service.getAllOffers();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long id) {
	return service.findOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Offer update(@PathVariable("id") Long id, @RequestBody OfferUpdateDto inputs) {
	return service.updateOffer(inputs, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(name = "id") Long id) {
	return service.deleteOffer(id);
    }
}
