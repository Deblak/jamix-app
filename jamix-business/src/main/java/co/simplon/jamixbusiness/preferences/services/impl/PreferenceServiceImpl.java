package co.simplon.jamixbusiness.preferences.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.preferences.Goal;
import co.simplon.jamixbusiness.preferences.Instrument;
import co.simplon.jamixbusiness.preferences.Style;
import co.simplon.jamixbusiness.preferences.repositories.GoalRepository;
import co.simplon.jamixbusiness.preferences.repositories.InstrumentRepository;
import co.simplon.jamixbusiness.preferences.repositories.StyleRepository;
import co.simplon.jamixbusiness.preferences.services.PreferenceService;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    private final InstrumentRepository instrumentRepository;
    private final StyleRepository styleRepository;
    private final GoalRepository goalRepository;

    protected PreferenceServiceImpl(InstrumentRepository instrumentRepository, StyleRepository styleRepository,
	    GoalRepository goalRepository) {
	this.instrumentRepository = instrumentRepository;
	this.styleRepository = styleRepository;
	this.goalRepository = goalRepository;
    }

    @Override
    public List<Instrument> getAllInstruments() {
	return instrumentRepository.findAll();
    }

    @Override
    public List<Style> getAllStyle() {
	return styleRepository.findAll();
    }

    @Override
    public List<Goal> getAllGoal() {
	return goalRepository.findAll();
    }

}
