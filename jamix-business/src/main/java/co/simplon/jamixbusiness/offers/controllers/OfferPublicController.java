package co.simplon.jamixbusiness.offers.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.offers.dtos.OfferMessageDto;
import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.services.OfferPublicService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

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

    @GetMapping
    public List<OfferViewDto> search(@ModelAttribute OfferSearchDto criteria) {
	return service.search(criteria);
    }

    @PostMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void contactAuthor(@PathVariable Long id, @RequestBody @Valid OfferMessageDto messageDto) {
	service.sendMail(id, messageDto);
    }
}
