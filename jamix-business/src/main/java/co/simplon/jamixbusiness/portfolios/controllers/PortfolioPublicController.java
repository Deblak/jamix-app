package co.simplon.jamixbusiness.portfolios.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioFullDto;
import co.simplon.jamixbusiness.portfolios.services.PortfolioPublicService;

@RestController
@RequestMapping("/portfolios")
public class PortfolioPublicController {

    private final PortfolioPublicService service;

    public PortfolioPublicController(PortfolioPublicService service) {
	this.service = service;
    }

    @GetMapping
    public List<PortfolioDto> getAll() {
	return service.getAll();
    }

    @GetMapping("/search")
    public List<PortfolioDto> search(String bandName) {
	return service.searchByBandName(bandName);
    }

    @GetMapping("/{id}")
    public PortfolioFullDto getById(@PathVariable Long id) {
	return service.getById(id);
    }
}
