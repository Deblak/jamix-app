package co.simplon.jamixbusiness.accounts;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.jamixbusiness.config.JwtProvider;
import co.simplon.jamixbusiness.security.Role;
import co.simplon.jamixbusiness.security.RoleRepository;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl {
    private final AccountRepository repository;
    private final JwtProvider provider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    protected AccountServiceImpl(AccountRepository repository, JwtProvider provider, PasswordEncoder passwordEncoder,
	    RoleRepository roleRepository) {
	this.repository = repository;
	this.provider = provider;
	this.passwordEncoder = passwordEncoder;
	this.roleRepository = roleRepository;
    }

    @Transactional
    public Account create(AccountCreateDto inputs) {
	Role roleDefault = roleRepository.findByRoleName("MUSICIAN").get();
	Account account = new Account();
	account.setUsername(inputs.username());
	account.setEmail(inputs.email());
	account.setPassword(passwordEncoder.encode(inputs.password()));
	account.setRole(roleDefault);
	return repository.save(account);
    }

    @Transactional
    public LoginResponse authenticated(AccountLoginDto inputs) {
	String email = inputs.email();
	String password = inputs.password();

	Account user = repository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("Invalid email"));

	if (!passwordEncoder.matches(password, user.getPassword())) {
	    throw new BadCredentialsException("Invalid password");
	}

	String username = user.getUsername();

	String roleName = user.getRole().getRoleName();
	Set<String> roleNames = Set.of(roleName);

	String token = provider.create(email, username, roleNames);

	return new LoginResponse(token, "Login successful");
    }

    public Optional<Account> getAuthenticatedAccount() {
	var authentication = SecurityContextHolder.getContext().getAuthentication();

	if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
	    String email = jwt.getSubject();
	    return repository.findByEmail(email);
	}

	return Optional.empty();
    }

    @Transactional
    public void deleteByEmail(String email) {
	Optional<Account> optional = repository.findByEmail(email);
	optional.ifPresent(repository::delete);
    }

}
