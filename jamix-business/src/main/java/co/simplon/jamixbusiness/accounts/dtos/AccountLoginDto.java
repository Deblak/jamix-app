package co.simplon.jamixbusiness.accounts.dtos;

public record AccountLoginDto(String email, String password) {
    @Override
    public String toString() {
	return String.format("{email=%s, password=[PROTECTED]}", email);
    }

}
