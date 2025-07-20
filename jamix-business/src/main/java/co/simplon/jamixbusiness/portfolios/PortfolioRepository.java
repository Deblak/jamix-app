package co.simplon.jamixbusiness.portfolios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.accounts.Account;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByAccount(Account account);

    List<Portfolio> findByBandNameContainingIgnoreCase(String bandName);

    boolean existsByAccount(Account account);
}
