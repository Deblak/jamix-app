package co.simplon.jamixbusiness.portfolios.dtos;

import jakarta.validation.constraints.Positive;

public record PortfolioDto(@Positive Long id, String bandName, String tagline, String biography, String imageUrl) {

}
