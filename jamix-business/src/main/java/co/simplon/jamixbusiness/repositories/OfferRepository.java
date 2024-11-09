package co.simplon.jamixbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    // Collection<AdView> findAllProjectedBy();
}
