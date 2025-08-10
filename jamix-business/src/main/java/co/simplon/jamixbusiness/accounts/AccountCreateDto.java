package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.validations.NoXSS;
import co.simplon.jamixbusiness.commons.validations.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateDto(

	@NotBlank @NoXSS(message = "Invalid usename format") String username,

	@NotBlank @Email @Email(message = "Invalid email format") String email,

	@NotBlank @PasswordStrength String password) {
}
