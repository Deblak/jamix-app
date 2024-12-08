package co.simplon.jamixbusiness.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@Profile("!prod")
public class SecurityConfig {
    @Value("${co.simplon.jamix.rounds}")
    private int rounds;

    @Value("${co.simplon.jamix.secret}")
    private String secret;

    @Value("${co.simplon.jamix.exp}")
    private Long exp;

    // Authorization server configuration
    @Bean
    public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(rounds);
    }

    @Bean
    JwtProvider jwtProvider() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(algorithm, exp);
    }

    // Ressource server configuration
    @Bean
    JwtDecoder jwtDecoder() {
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA256");
	NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
	return decoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		// Multiple matchers to map verbs + paths + authorizations
		// "authorizations": anonymous, permit, deny and more...
		// By configuration (filterChain), also by annotations...
		.authorizeHttpRequests(
			(req) -> req.requestMatchers(HttpMethod.POST, "/signup", "/signup/authenticate").anonymous())
		// Always last rule:
		.authorizeHttpRequests((reqs) -> reqs.anyRequest().authenticated())
		.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults()))
		// The build method builds the configured SecurityFilterChain
		// with all the specified configuration
		.build();
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
	return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(DataAccessException ex, Object object,
	    HttpHeaders httpHeaders, HttpStatus conflict, WebRequest request) {
	// TODO Auto-generated method stub
	return null;
    }

}
