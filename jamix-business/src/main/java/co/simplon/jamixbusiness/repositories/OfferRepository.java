package co.simplon.jamixbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Account;
import co.simplon.jamixbusiness.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllProjectedBy();

    List<Offer> findByTitleContainingIgnoreCase(String keyword);

    List<Offer> findByAccount(Account account);
}
