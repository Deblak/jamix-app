package co.simplon.jamixbusiness.portfolios.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PortfolioDto(@Positive Long id, @NotBlank String bandName, String tagline, String biography,
	String imageUrl) {

}
