package co.simplon.jamixbusiness.accounts;

public record AuthInfo(String token) {
    @Override
    public String toString() {
	return "{token=[PROTECTED]}, accountInfo=%s}".formatted();
    }
}
