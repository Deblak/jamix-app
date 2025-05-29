package co.simplon.jamixbusiness.offers.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferUpdateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.services.OfferMusicianService;
import jakarta.validation.Valid;

@RequestMapping("/offers")
@RestController
public class OfferMusicianController {

    private final OfferMusicianService service;

    public OfferMusicianController(OfferMusicianService service) {
	this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OfferViewDto create(@Valid @ModelAttribute OfferCreateDto dto,
	    @RequestParam(value = "image", required = false) MultipartFile image) {
	return service.create(dto, image);
    }

    @GetMapping("/my-offers")
    public List<OfferViewDto> listMine() {
	return service.listMine();
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OfferViewDto update(@PathVariable Long id, @ModelAttribute OfferUpdateDto dto,
	    @RequestParam(value = "image", required = false) MultipartFile image) {
	return service.update(id, dto, image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
	service.delete(id);
    }

    @PostMapping(path = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OfferViewDto uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
	return service.uploadImage(id, image);
    }
}
