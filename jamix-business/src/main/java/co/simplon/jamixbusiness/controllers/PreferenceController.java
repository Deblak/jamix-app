package co.simplon.jamixbusiness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.entities.Goal;
import co.simplon.jamixbusiness.entities.Instrument;
import co.simplon.jamixbusiness.entities.Style;
import co.simplon.jamixbusiness.services.impl.PreferenceServiceImpl;

@RestController
@RequestMapping("/api")
public class PreferenceController {

    private final PreferenceServiceImpl service;

    protected PreferenceController(PreferenceServiceImpl service) {
	this.service = service;
    }

    @GetMapping("/instruments")
    public List<Instrument> getAllInstruments() {
	return service.getAllInstruments();
    }

    @GetMapping("/styles")
    public List<Style> getAllStyles() {
	return service.getAllStyle();
    }

    @GetMapping("/goals")
    public List<Goal> getAllGoals() {
	return service.getAllGoal();
    }

}
