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
    public OfferViewDto create(@Valid @ModelAttribute OfferCreateDto dto, MultipartFile image) {
	return service.create(dto, image);
    }

    @GetMapping("/owned")
    public List<OfferViewDto> getOwnedOffers() {
	return service.getOwnedOffers();
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OfferViewDto update(@PathVariable Long id, @ModelAttribute @Valid OfferUpdateDto dto) {
	return service.update(id, dto);
    }
    /*
     * @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces =
     * MediaType.APPLICATION_JSON_VALUE) public PortfolioDto
     * updateOwned(@ModelAttribute @Valid PortfolioUpdateDto dto) { return
     * service.update(dto); }
     */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
	service.delete(id);
    }

    @PostMapping(path = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OfferViewDto uploadImage(@PathVariable Long id, @RequestParam MultipartFile image) {
	return service.uploadImage(id, image);
    }
}
