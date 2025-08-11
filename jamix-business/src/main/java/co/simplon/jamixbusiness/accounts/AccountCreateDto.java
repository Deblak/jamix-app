package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.contraints.AccountUniqueEmail;
import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import co.simplon.jamixbusiness.commons.contraints.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateDto(

	@NotBlank @NoXSS(message = "Invalid usename format") String username,

	@NotBlank @Email(message = "Invalid email format") @AccountUniqueEmail(message = "Email already exists") String email,

	@NotBlank @PasswordStrength String password) {
}
