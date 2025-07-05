package co.simplon.jamixbusiness.locations;

import co.simplon.jamixbusiness.commons.validations.CleanXSS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationCreateDto(

	@NotBlank @CleanXSS(message = "Invalid city format") @Size(max = 50) String city,

	@NotBlank @CleanXSS(message = "Invalid zipCode format") @Size(max = 5) String zipCode) {

}
