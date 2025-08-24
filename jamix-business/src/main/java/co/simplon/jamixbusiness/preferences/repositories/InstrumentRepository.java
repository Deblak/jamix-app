package co.simplon.jamixbusiness.preferences.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.preferences.entities.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    List<Instrument> findAllByOrderByNameAsc();
}
