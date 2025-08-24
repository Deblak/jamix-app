package co.simplon.jamixbusiness.preferences.services;

import java.util.List;

import co.simplon.jamixbusiness.preferences.entities.Goal;
import co.simplon.jamixbusiness.preferences.entities.Instrument;
import co.simplon.jamixbusiness.preferences.entities.Style;

public interface PreferenceService {

    List<Instrument> getAllInstruments();

    List<Style> getAllStyle();

    List<Goal> getAllGoal();

}
