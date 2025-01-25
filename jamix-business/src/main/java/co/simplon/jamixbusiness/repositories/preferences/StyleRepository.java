package co.simplon.jamixbusiness.repositories.preferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.preferences.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {

    List<Style> findByNameIn(List<String> styleNames);

}
