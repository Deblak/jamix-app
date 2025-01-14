package co.simplon.jamixbusiness.dtos;

public record UserCreate(String username, String email, String password) {
    @Override
    public String toString() {
	return String.format("{username=%s, password=[PROTECTED]}", username);
    }
}
