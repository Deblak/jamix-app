package co.simplon.jamixbusiness.offers.dtos;

import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactMusician(
	@NotBlank @Size(min = 1, max = 100) @NoXSS(message = "HTML tags are not allowed") String firstname,

	@NotBlank @Size(min = 1, max = 100) @NoXSS(message = "HTML tags are not allowed") String lastname,

	@Size(min = 6, max = 255) @Email(message = "Invalid email format") String contactMail,

	@Size(min = 1, max = 250) @NoXSS(message = "HTML tags are not allowed") String comments) {

}
