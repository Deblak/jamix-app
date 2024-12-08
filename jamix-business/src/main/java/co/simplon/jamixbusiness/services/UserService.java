package co.simplon.jamixbusiness.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.jamixbusiness.config.JwtProvider;
import co.simplon.jamixbusiness.dtos.UserCreate;
import co.simplon.jamixbusiness.dtos.UserLogIn;
import co.simplon.jamixbusiness.entities.UserAccount;
import co.simplon.jamixbusiness.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;
    private final JwtProvider provider;
    private final PasswordEncoder passwordEncoder;

    protected UserService(UserRepository repository, JwtProvider provider, PasswordEncoder passwordEncoder) {
	this.repository = repository;
	this.provider = provider;
	this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(UserCreate inputs) {
	UserAccount user = new UserAccount();
	user.setUsername(inputs.username());
	user.setEmail(inputs.email());
	user.setPassword(passwordEncoder.encode(inputs.password()));
	repository.save(user);
    }

    public Object authenticated(UserLogIn inputs) {
	String username = inputs.username();
	String password = inputs.password();

	UserAccount entity = repository.findByUsernameIgnoreCase(username)
		.orElseThrow(() -> new BadCredentialsException(username));

	if (!passwordEncoder.matches(password, entity.getPassword())) {
	    throw new BadCredentialsException(username);
	}

	String sessionProvider = provider.create(username);
	return sessionProvider;
    }

}
