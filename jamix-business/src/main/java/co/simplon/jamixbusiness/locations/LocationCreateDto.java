package co.simplon.jamixbusiness.locations;

import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationCreateDto(

	@NotBlank @NoXSS(message = "Invalid city format") @Size(max = 50) String city,

	@NotBlank @NoXSS(message = "Invalid zipCode format") @Size(max = 5) String zipCode) {

}
