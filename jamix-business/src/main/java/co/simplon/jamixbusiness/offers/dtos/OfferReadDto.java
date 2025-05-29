package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

public record OfferReadDto(String title, String city, String zipCode, String imageUrl, String contactMail,
	String instrumentName, String styleName, String goalType, String description, LocalDate createdAt) {
}
