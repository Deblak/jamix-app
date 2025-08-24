package co.simplon.jamixbusiness.offers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.accounts.entities.Account;
import co.simplon.jamixbusiness.offers.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {

    List<Offer> findTop3ByOrderByCreatedAtDesc();

    List<Offer> findByAccount(Account account);

    boolean existsByTitleIgnoreCaseAndContactMail(String title, String contactMail);
}
