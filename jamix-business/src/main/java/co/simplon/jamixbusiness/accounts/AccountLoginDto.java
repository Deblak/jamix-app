package co.simplon.jamixbusiness.accounts;

import jakarta.validation.constraints.Email;

public record AccountLoginDto(@Email(message = "Invalid email format") String email, String password) {
    @Override
    public String toString() {
	return "{email=%s, password=[PROTECTED]}".formatted(email);
    }

}
