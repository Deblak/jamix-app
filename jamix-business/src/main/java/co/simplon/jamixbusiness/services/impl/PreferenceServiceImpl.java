package co.simplon.jamixbusiness.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.entities.Goal;
import co.simplon.jamixbusiness.entities.Instrument;
import co.simplon.jamixbusiness.entities.Style;
import co.simplon.jamixbusiness.repositories.GoalRepository;
import co.simplon.jamixbusiness.repositories.InstrumentRepository;
import co.simplon.jamixbusiness.repositories.StyleRepository;
import co.simplon.jamixbusiness.services.PreferenceService;

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
    public List<Instrument> getAllInstrument() {
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
