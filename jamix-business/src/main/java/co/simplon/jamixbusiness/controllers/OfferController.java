package co.simplon.jamixbusiness.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	this.service = service;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Offer create(@Valid @ModelAttribute OfferCreateDto inputs, @RequestParam("image") MultipartFile imageFile) {
	return service.create(inputs, imageFile);
    }

    @GetMapping
    public List<Offer> getAllOffers() {
	return service.getAll();
    }

    /**
     * futur /{accoundId}/offers
     *
     * @return
     */
    @GetMapping("/my-offer")
    public List<Offer> getMyOffers() {
	return service.getAll();
    }

    @DeleteMapping("/my-offer/{id}")
    public boolean delete(@PathVariable(name = "id") Long id) {
	return service.delete(id);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long id) {
	return service.getById(id);
    }

    @GetMapping("/search")
    public List<Offer> searchOffers(@RequestParam String keyword) {
	return service.findByTitle(keyword);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Offer update(@PathVariable("id") Long id, @RequestBody OfferUpdateDto inputs,
	    @RequestParam("image") MultipartFile imageFile) {
	return service.update(inputs, id, imageFile);
    }

}
