package co.simplon.jamixbusiness.services;

import java.util.List;

import co.simplon.jamixbusiness.entities.preferences.Goal;
import co.simplon.jamixbusiness.entities.preferences.Instrument;
import co.simplon.jamixbusiness.entities.preferences.Style;

public interface PreferenceService {

    List<Instrument> getAllInstrument();

    List<Style> getAllStyle();

    List<Goal> getAllGoal();

}
