package co.simplon.jamixbusiness.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    @Override
    List<Instrument> findAll(Sort sort);
}
