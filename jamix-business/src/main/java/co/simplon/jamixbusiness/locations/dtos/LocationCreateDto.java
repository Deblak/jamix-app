package co.simplon.jamixbusiness.locations.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationCreateDto(

	@NotBlank @Size(max = 50) String city,

	@NotBlank @Size(max = 5) String zipCode) {

}
