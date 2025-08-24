package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.contraints.AccountUniqueEmail;
import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import co.simplon.jamixbusiness.commons.contraints.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreateDto(

	@NotBlank @Size(min = 1, max = 20) @NoXSS(message = "Invalid usename format") String username,

	@NotBlank @Size(min = 6, max = 320) @Email(message = "Invalid email format") @AccountUniqueEmail(message = "Email already exists") String email,

	@NotBlank @PasswordStrength String password) {
}
