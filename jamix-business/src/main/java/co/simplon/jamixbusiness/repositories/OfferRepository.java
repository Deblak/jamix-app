package co.simplon.jamixbusiness.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Account;
import co.simplon.jamixbusiness.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllProjectedBy();

    // Set<OfferViewDto> findByAccount(Account account);

    List<Offer> findByTitleContaining(String keyword);

    List<Offer> findByInstrument_Name(String instrumentName);

    List<Offer> findByStyle_Name(String styleName);

    List<Offer> findByGoal_Type(String goalType);

    Set<Offer> findByAccount(Account account);
}
