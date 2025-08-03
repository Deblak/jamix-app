package co.simplon.jamixbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Value("${co.simplon.jamix.cors}")
    private String allowedOrigins;

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
			.requestMatchers(HttpMethod.DELETE, "/offers/**", "/images/**", "/portfolios/owned")
			.authenticated().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
			.access(new WebExpressionAuthorizationManager("!environment.acceptsProfiles('prod')")))
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
    }

    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }
}
