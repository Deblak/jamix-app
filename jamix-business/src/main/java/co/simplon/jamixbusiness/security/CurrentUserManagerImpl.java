package co.simplon.jamixbusiness.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.accounts.AccountRepository;

@Component
public class CurrentUserManagerImpl implements CurrentUserManager {

    private final AccountRepository accountRepository;

    public CurrentUserManagerImpl(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    @Override
    public Account getCurrentAccount() {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth == null || !auth.isAuthenticated()) {
	    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
	}
	String email = auth.getName();
	return accountRepository.findByEmail(email).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user not found"));
    }
}
