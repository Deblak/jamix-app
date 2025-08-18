package co.simplon.jamixbusiness.portfolios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.portfolios.dtos.PortfolioCreateDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioUpdateDto;
import co.simplon.jamixbusiness.portfolios.services.PortfolioMusicianService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/portfolios/owned")
public class PortfolioMusicianController {

    private final PortfolioMusicianService service;

    public PortfolioMusicianController(PortfolioMusicianService service) {
	this.service = service;
    }

    @GetMapping
    public PortfolioDto getOwnedPortfolio() {
	return service.getOwnedPortfolio();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioDto create(@Valid @ModelAttribute PortfolioCreateDto dto, MultipartFile image) {
	return service.create(dto, image);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PortfolioDto updateOwned(@ModelAttribute @Valid PortfolioUpdateDto dto) {
	return service.update(dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
	service.delete();
    }
}
