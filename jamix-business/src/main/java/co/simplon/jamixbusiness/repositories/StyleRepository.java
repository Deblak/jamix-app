package co.simplon.jamixbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {

    List<Style> findByNameInOrderByNameAsc(List<String> styleNames);

    List<Style> findByNameIn(List<String> styleNames);

}
