package co.simplon.jamixbusiness.preferences;

import java.util.List;

public interface PreferenceService {

    List<Instrument> getAllInstruments();

    List<Style> getAllStyle();

    List<Goal> getAllGoal();

}
