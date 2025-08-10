package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Positive;

public record OfferViewDto(@Positive Long id, String title, String imageUrl, String contactMail,
	@Positive Long locationId, String city, String zipCode, @Positive Long instrumentId, String instrumentName,
	@Positive Long styleId, String styleName, @Positive Long goalId, String goalType, String description,
	LocalDate createdAt, PortfolioLinkDto portfolioLinkDto) {
}
