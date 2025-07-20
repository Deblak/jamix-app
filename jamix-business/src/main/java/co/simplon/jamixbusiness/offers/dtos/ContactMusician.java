package co.simplon.jamixbusiness.offers.dtos;

import co.simplon.jamixbusiness.commons.validations.NoXSS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactMusician(@NotBlank @Size(max = 10) @NoXSS String firstname, @NotBlank @NoXSS String lastname,
	@NotBlank String contactMail, @Size(max = 250) @NoXSS String comments) {

}
