package co.simplon.jamixbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    // Collection<OfferView> findAllProjectedBy();
}
