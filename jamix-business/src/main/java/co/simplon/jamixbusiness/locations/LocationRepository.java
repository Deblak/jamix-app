package co.simplon.jamixbusiness.locations;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByCityIgnoreCaseAndZipCode(String city, String zipCode);

    Optional<Location> findByCityIgnoreCaseAndZipCode(String city, String zipCode);

}
