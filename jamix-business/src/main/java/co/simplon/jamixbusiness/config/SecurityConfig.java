package co.simplon.jamixbusiness.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
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

import com.auth0.jwt.algorithms.Algorithm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@Profile("!prod")
@OpenAPIDefinition(security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
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
    PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(rounds);
    }

    @Bean
    JwtProvider jwtProvider() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(algorithm, exp, issuer);
    }

    @Bean
    JwtDecoder jwtDecoder() {
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

	OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);
	decoder.setJwtValidator(validator);

	return decoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
			.requestMatchers(HttpMethod.GET, "/offers/owned", "/portfolios/owned").authenticated()
			.requestMatchers(HttpMethod.GET, "/offers/**", "/api/**", "/images/**", "/portfolios/**")
			.permitAll().requestMatchers(HttpMethod.POST, "/offers", "/images/**", "/portfolios/owned")
			.authenticated()
			.requestMatchers(HttpMethod.PATCH, "/offers/**", "/images/**", "/portfolios/owned")
			.authenticated()
			.requestMatchers(HttpMethod.DELETE, "/offers/**", "/images/**", "portfolios/owned")
			.authenticated().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
			.permitAll())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
    }

}
