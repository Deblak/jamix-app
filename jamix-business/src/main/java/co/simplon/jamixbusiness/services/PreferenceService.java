package co.simplon.jamixbusiness.services;

import java.util.List;

import co.simplon.jamixbusiness.entities.Goal;
import co.simplon.jamixbusiness.entities.Instrument;
import co.simplon.jamixbusiness.entities.Style;

public interface PreferenceService {

    List<Instrument> getAllInstrument();

    List<Style> getAllStyle();

    List<Goal> getAllGoal();

}
