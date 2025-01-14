package co.simplon.jamixbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByTitleContaining(String keyword);

    List<Offer> findByInstrument_Name(String instrumentName);

    List<Offer> findByStyle_Name(String styleName);

    List<Offer> findByGoal_Name(String goalName);
}
