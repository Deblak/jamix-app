package co.simplon.jamixbusiness.portfolios.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.accounts.AccountRepository;
import co.simplon.jamixbusiness.commons.images.ImageService;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioCreateDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioUpdateDto;
import co.simplon.jamixbusiness.portfolios.entities.Portfolio;
import co.simplon.jamixbusiness.portfolios.mappers.PortfolioMapper;
import co.simplon.jamixbusiness.portfolios.repositories.PortfolioRepository;
import co.simplon.jamixbusiness.portfolios.services.PortfolioMusicianService;
import co.simplon.jamixbusiness.security.CurrentUserManagerImpl;

@Service
@Transactional
public class PortfolioMusicianServiceImpl implements PortfolioMusicianService {

    private final PortfolioRepository repository;
    private final CurrentUserManagerImpl currentUserManager;
    private final PortfolioMapper mapper;
    private final ImageService imageService;

    public PortfolioMusicianServiceImpl(PortfolioRepository repository,
	    CurrentUserManagerImpl currentUserManager, PortfolioMapper mapper, ImageService imageService) {
	this.repository = repository;
	this.currentUserManager = currentUserManager;
	this.mapper = mapper;
	this.imageService = imageService;
    }

    @Override
    @Transactional(readOnly = true)
    public PortfolioDto getOwnedPortfolio() {
	Account account = currentUserManager.getCurrentAccount();
	Portfolio portfolio = repository.findByAccount(account).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "portfolio not found for account"));
	return mapper.mapToDto(portfolio);
    }

    @Override
    public PortfolioDto create(PortfolioCreateDto dto, MultipartFile image) {
	Account account = currentUserManager.getCurrentAccount();
	if (repository.existsByAccount(account)) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "portfolio.exists");
	}
	Portfolio portfolio = mapper.mapCreateDtoToEntity(dto, account);
	// Optional image handling
	if (image != null && !image.isEmpty()) {
	    String imageId = imageService.store(image);
	    portfolio.setImageId(imageId);
	}
	Portfolio saved = repository.save(portfolio);
	return mapper.mapToDto(saved);
    }

    @Override
    public PortfolioDto update(PortfolioUpdateDto dto) {
	Account account = currentUserManager.getCurrentAccount();
	Portfolio portfolio = repository.findByAccount(account)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
			"Portfolio not found for account " + account.getEmail()));
	portfolio.setBandName(dto.bandName());
	if (dto.tagline() != null) {
	    portfolio.setTagline(dto.tagline());
	}
	if (dto.biography() != null) {
	    portfolio.setBiography(dto.biography());
	}

	MultipartFile img = dto.image();
	if (img != null && !img.isEmpty()) {
	    if (portfolio.getImageId() != null) {
		imageService.delete(portfolio.getImageId());
	    }
	    String newId = imageService.store(img);
	    portfolio.setImageId(newId);
	}

	Portfolio saved = repository.save(portfolio);
	return mapper.mapToDto(saved);
    }

    @Override
    public void delete() {
	Account account = currentUserManager.getCurrentAccount();
	Portfolio portfolio = repository.findByAccount(account).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "portfolio.not.found.for.account"));
	repository.delete(portfolio);
    }

    @Override
    @Transactional
    public PortfolioDto uploadImage(Long id, MultipartFile image) {
	Account account = currentUserManager.getCurrentAccount();
	Portfolio portfolio = repository.findByAccount(account).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "portfolio not found for account"));

	if (!portfolio.getAccount().equals(account)) {
	    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot update another user’s portfolio");
	}

	String imageId = imageService.store(image);
	portfolio.setImageId(imageId);

	Portfolio saved = repository.save(portfolio);
	return mapper.mapToDto(saved);
    }
}