package co.simplon.jamixbusiness.preferences.services;

import java.util.List;

import co.simplon.jamixbusiness.preferences.Goal;
import co.simplon.jamixbusiness.preferences.Instrument;
import co.simplon.jamixbusiness.preferences.Style;

public interface PreferenceService {

    List<Instrument> getAllInstruments();

    List<Style> getAllStyle();

    List<Goal> getAllGoal();

}
