package co.simplon.jamixbusiness.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.jamixbusiness.config.JwtProvider;
import co.simplon.jamixbusiness.dtos.AccountCreateDto;
import co.simplon.jamixbusiness.dtos.AccountLoginDto;
import co.simplon.jamixbusiness.entities.Account;
import co.simplon.jamixbusiness.repositories.AccountRepository;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository repository;
    private final JwtProvider provider;
    private final PasswordEncoder passwordEncoder;

    protected AccountService(AccountRepository repository, JwtProvider provider, PasswordEncoder passwordEncoder) {
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

//    public Object authenticated(UserLogIn inputs) {
//	String username = inputs.username();
//	String password = inputs.password();
//
//	UserAccount entity = repository.findByUsernameIgnoreCase(username)
//		.orElseThrow(() -> new BadCredentialsException(username));
//
//	if (!passwordEncoder.matches(password, entity.getPassword())) {
//	    throw new BadCredentialsException(username);
//	}
//
//	String sessionProvider = provider.create(username);
//	return sessionProvider;
//    }

    public String authenticated(AccountLoginDto inputs) {
	String username = inputs.username();
	String password = inputs.password();

	Account user = repository.findByUsernameIgnoreCase(username)
		.orElseThrow(() -> new BadCredentialsException("Invalid username"));

	if (!passwordEncoder.matches(password, user.getPassword())) {
	    throw new BadCredentialsException("Invalid password");
	}

	return provider.create(username);
    }

}
