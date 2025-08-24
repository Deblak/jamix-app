package co.simplon.jamixbusiness.preferences;

import java.util.List;

import org.springframework.stereotype.Service;

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
	return styleRepository.findAll();
    }

    @Override
    public List<Goal> getAllGoal() {
	return goalRepository.findAll();
    }

}
