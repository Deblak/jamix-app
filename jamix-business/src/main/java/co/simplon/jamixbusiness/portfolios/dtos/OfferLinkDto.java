package co.simplon.jamixbusiness.portfolios.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Positive;

public record OfferLinkDto(@Positive Long id, String title, String imageUrl, String city, String zipCode,
	String instrumentName, String styleName, String goalType, LocalDate createdAt) {

}
