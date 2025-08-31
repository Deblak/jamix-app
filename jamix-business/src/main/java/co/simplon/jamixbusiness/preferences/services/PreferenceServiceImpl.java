package co.simplon.jamixbusiness.preferences.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.preferences.entities.Goal;
import co.simplon.jamixbusiness.preferences.entities.Instrument;
import co.simplon.jamixbusiness.preferences.entities.Style;
import co.simplon.jamixbusiness.preferences.repositories.GoalRepository;
import co.simplon.jamixbusiness.preferences.repositories.InstrumentRepository;
import co.simplon.jamixbusiness.preferences.repositories.StyleRepository;

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
	return instrumentRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Style> getAllStyle() {
	return styleRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Goal> getAllGoal() {
	return goalRepository.findAllByOrderByTypeAsc();
    }

}
