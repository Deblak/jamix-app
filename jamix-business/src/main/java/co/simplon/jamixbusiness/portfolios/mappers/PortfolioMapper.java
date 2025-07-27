package co.simplon.jamixbusiness.portfolios.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioCreateDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioDto;
import co.simplon.jamixbusiness.portfolios.dtos.PortfolioUpdateDto;
import co.simplon.jamixbusiness.portfolios.entities.Portfolio;

@Component
public class PortfolioMapper {

    public Portfolio mapCreateDtoToEntity(PortfolioCreateDto dto, Account account) {
	Portfolio portfolio = new Portfolio();
	portfolio.setBandName(dto.bandName());
	portfolio.setTagline(dto.tagline());
	portfolio.setBiography(dto.biography());
	portfolio.setAccount(account);
	return portfolio;
    }

    public PortfolioDto mapToDto(Portfolio portfolio) {
	return new PortfolioDto(portfolio.getId(), portfolio.getBandName(), portfolio.getTagline(),
		portfolio.getBiography(), portfolio.getImageId());
    }

    public void patchEntityFromDto(PortfolioUpdateDto dto, Portfolio portfolio) {
	if (dto.bandName() != null) {
	    portfolio.setBandName(dto.bandName());
	}
	if (dto.tagline() != null) {
	    portfolio.setTagline(dto.tagline());
	}
	if (dto.biography() != null) {
	    portfolio.setBiography(dto.biography());
	}
    }

    public List<PortfolioDto> mapListToDto(List<Portfolio> entities) {
	if (entities == null || entities.isEmpty()) {
	    return List.of();
	}
	return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
