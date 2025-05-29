package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Positive;

public record OfferViewDto(@Positive Long id, String title, String city, String zipCode, String imageUrl,
	String contactMail, @Positive Long instrumentId, @Positive Long styleId, @Positive Long goalId,
	String description, LocalDate createdAt) {
}
