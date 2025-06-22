package co.simplon.jamixbusiness.preferences;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    @Override
    List<Instrument> findAll(Sort sort);
}
