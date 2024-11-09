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

import co.simplon.jamixbusiness.dtos.AdCreateDto;
import co.simplon.jamixbusiness.dtos.AdUpdateDto;
import co.simplon.jamixbusiness.entities.Ad;
import co.simplon.jamixbusiness.services.impl.AdServiceImpl;
import jakarta.validation.Valid;

@RequestMapping("/ads")
@RestController
public class AdController {

    private final AdServiceImpl service;

    public AdController(AdServiceImpl service) {
	super();
	this.service = service;
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody AdCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping
    public List<Ad> getAllAds() {
	return service.getAllAds();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Ad update(@PathVariable("id") Long id, @RequestBody AdUpdateDto inputs) {
	return service.updateAd(inputs, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(name = "id") Long id) {
	return service.deleteAd(id);
    }
}
