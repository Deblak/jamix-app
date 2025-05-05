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
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@Profile("!prod")
public class SecurityConfig {
    @Value("${co.simplon.jamix.cors}")
    private String allowedOrigins;

    @Value("${co.simplon.bcrypt.rounds}")
    private int rounds;

    @Value("${co.simplon.jwt.secret}")
    private String secret;

    @Value("${co.simplon.jwt.exp}")
    private Long exp;

    @Value("${co.simplon.jwt.issuer}")
    private String issuer;

    @Bean
    public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(rounds);
    }

    @Bean
    JwtProvider jwtProvider() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(algorithm, exp, issuer);
    }

    @Bean
    JwtDecoder jwtDecoder() {
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA256");
	NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

	OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);

	return decoder;
    }

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(
//			(req) -> req.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
//				.requestMatchers(HttpMethod.POST, "/offers/create").authenticated()
//				.requestMatchers(HttpMethod.DELETE, "/my-offer/**").authenticated()
//				.requestMatchers(HttpMethod.DELETE, "/offers/**").authenticated()
//				.requestMatchers(HttpMethod.GET, "/offers/create").authenticated()
//				.requestMatchers(HttpMethod.GET, "/offers/**", "/api/**").permitAll()
//				.requestMatchers("/images/**").permitAll())
//		.authorizeHttpRequests((reqs) -> reqs.anyRequest().authenticated())
//		.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults())).build();
//    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
			// signup / login restent open anonymous
			.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
			.requestMatchers(HttpMethod.GET, "/offers/my-offers").authenticated()
			// GET /offers/** (y compris /offers/search) est public
			.requestMatchers(HttpMethod.GET, "/offers/**").permitAll()
			// POST /offers, PUT & DELETE = authentification
			.requestMatchers(HttpMethod.POST, "/offers").authenticated()
			.requestMatchers(HttpMethod.PUT, "/offers/**").authenticated()
			.requestMatchers(HttpMethod.DELETE, "/offers/**").authenticated()
			// images sont publiques
			.requestMatchers("/images/**").permitAll()
			// le reste = authentification required
			.anyRequest().authenticated())
		// Resource server JWT (ou adaptez selon votre auth)
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	http
//		// desactive les CSRF pour les API REST stateless
//		.csrf(csrf -> csrf.disable())
//		// regles d’autorisation
//		.authorizeHttpRequests(auth -> auth
//			// GET /offers/** accessibles à tous
//			.requestMatchers(HttpMethod.GET, "/offers/**").permitAll()
//			// POST/PUT/DELETE /offers/** reserves aux musiciens
//			.requestMatchers("/offers/**").hasRole("MUSICIAN")
//			// toute autre requete necessite authentification
//			.anyRequest().authenticated())
//		// configuration du resource server JWT
//		.oauth2ResourceServer(oauth2 -> oauth2.jwt());
//
//	return http.build();
//    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
	return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(DataAccessException ex, Object object,
	    HttpHeaders httpHeaders, HttpStatus conflict, WebRequest request) {
	return null;
    }

}
