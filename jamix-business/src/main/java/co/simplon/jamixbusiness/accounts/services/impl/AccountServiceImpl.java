package co.simplon.jamixbusiness.accounts.services.impl;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.accounts.dtos.AccountCreateDto;
import co.simplon.jamixbusiness.accounts.dtos.AccountLoginDto;
import co.simplon.jamixbusiness.accounts.repositories.AccountRepository;
import co.simplon.jamixbusiness.config.JwtProvider;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl {
    private final AccountRepository repository;
    private final JwtProvider provider;
    private final PasswordEncoder passwordEncoder;

    protected AccountServiceImpl(AccountRepository repository, JwtProvider provider, PasswordEncoder passwordEncoder) {
	this.repository = repository;
	this.provider = provider;
	this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(AccountCreateDto inputs) {
	Account user = new Account();
	user.setUsername(inputs.username());
	user.setEmail(inputs.email());
	user.setPassword(passwordEncoder.encode(inputs.password()));
	repository.save(user);
    }

    public String authenticated(AccountLoginDto inputs) {
	String email = inputs.email();
	String password = inputs.password();

	Account user = repository.findByEmailIgnoreCase(email)
		.orElseThrow(() -> new BadCredentialsException("Invalid email"));

	if (!passwordEncoder.matches(password, user.getPassword())) {
	    throw new BadCredentialsException("Invalid password");
	}
//	String sessionProvider = provider.create(username);
//	return sessionProvider;
	return provider.create(email);
    }

    public Optional<Account> getAuthenticatedAccount() {
	var authentication = SecurityContextHolder.getContext().getAuthentication();

	if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
	    String email = jwt.getSubject();
	    return repository.findByEmailIgnoreCase(email);
	}

	return Optional.empty();
    }

}
