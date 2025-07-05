package co.simplon.jamixbusiness.preferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Long> {

    List<Style> findByNameInOrderByNameAsc(List<String> styleNames);

    List<Style> findByNameIn(List<String> styleNames);

}
