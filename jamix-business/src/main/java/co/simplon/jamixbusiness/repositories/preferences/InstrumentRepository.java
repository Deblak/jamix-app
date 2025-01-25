package co.simplon.jamixbusiness.repositories.preferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.preferences.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    List<Instrument> findByNameIn(List<String> instrumentNames);

}
