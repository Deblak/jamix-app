package co.simplon.jamixbusiness.accounts.dtos;

public record AuthInfo(String token) {
    @Override
    public String toString() {
	return String.format("{token=[PROTECTED]}, accountInfo=%s}");
    }
}
