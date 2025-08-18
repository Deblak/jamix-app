package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.contraints.PasswordStrength;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountLoginDto(@NotBlank @Size(min = 6, max = 320) @Email(message = "Invalid email format") String email,
	@NotBlank @PasswordStrength String password) {
    @Override
    public String toString() {
	return "{email=%s, password=[PROTECTED]}".formatted(email);
    }

}
