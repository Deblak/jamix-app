package co.simplon.jamixbusiness.accounts.dtos;

import co.simplon.jamixbusiness.commons.validations.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateDto(

	@NotBlank String username,

	@NotBlank @Email String email,

	@NotBlank @PasswordStrength String password) {

    @Override
    public String toString() {
	return String.format("{username=%s, email=%s, password=[PROTECTED]}", username, email);
    }
}
