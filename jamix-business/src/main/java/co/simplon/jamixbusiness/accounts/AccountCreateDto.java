package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.validations.CleanXSS;
import co.simplon.jamixbusiness.commons.validations.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateDto(

	@NotBlank @CleanXSS(message = "Invalid usename format") String username,

	@NotBlank @Email @Email(message = "Invalid email format") String email,

	@NotBlank @PasswordStrength String password) {

    @Override
    public String toString() {
	return "{username=%s, email=%s, password=[PROTECTED]}".formatted(username, email);
    }
}
