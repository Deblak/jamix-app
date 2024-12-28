package co.simplon.jamixbusiness.dtos;

import jakarta.validation.constraints.Size;

public record OfferUpdateDto(

	@Size(max = 200) String title,

	@Size(max = 600) String description,

	@Size(max = 50) String city,

	@Size(max = 5) String zipCode,

	@Size(max = 255) String mail) {

}
