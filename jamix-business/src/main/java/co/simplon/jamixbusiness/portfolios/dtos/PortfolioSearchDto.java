package co.simplon.jamixbusiness.portfolios.dtos;

import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.Size;

public record PortfolioSearchDto(
	@Size(max = 50, message = "Band name don't exceed 50 characters") @NoXSS(message = "HTML tags are not allowed") String bandName) {

}
