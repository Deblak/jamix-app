package co.simplon.jamixbusiness.portfolios.services;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.portfolios.dtos.PortfolioCreateDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioUpdateDto;

public interface PortfolioMusicianService {

    PortfolioDto create(PortfolioCreateDto dto, MultipartFile image);

    PortfolioDto getOwnedPortfolio();

    PortfolioDto update(PortfolioUpdateDto dto);

    void delete();

    PortfolioDto uploadImage(Long id, MultipartFile image);
}
