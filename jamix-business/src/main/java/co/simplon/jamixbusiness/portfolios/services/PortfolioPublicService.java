package co.simplon.jamixbusiness.portfolios.services;

import java.util.List;
import java.util.Optional;

import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioFullDto;

public interface PortfolioPublicService {
    List<PortfolioDto> getAll();

    PortfolioFullDto getById(Long id);

    List<PortfolioDto> searchByBandName(String bandName);

    Optional<PortfolioDto> getByOfferId(Long offerId);
}
