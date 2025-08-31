package co.simplon.jamixbusiness.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${jamix.cors}")
    private String allowedOrigins;

    private static final String PATH_PORTFOLIOS_OWNED = "/portfolios/owned";
    private static final String PATH_OFFERS = "/offers/**";
    private static final String PATH_IMAGES = "/images/**";

    @Bean
    @Profile("!prod")
    SecurityFilterChain devFilterChain(HttpSecurity http) throws Exception {
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
			.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET, "/offers/owned", PATH_PORTFOLIOS_OWNED).authenticated()
			.requestMatchers(HttpMethod.GET, PATH_OFFERS, "/api/**", PATH_IMAGES, "/portfolios/**")
			.permitAll().requestMatchers(HttpMethod.POST, "/offers/contact/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/offers", PATH_PORTFOLIOS_OWNED, PATH_IMAGES).authenticated()
			.requestMatchers(HttpMethod.PATCH, PATH_OFFERS, PATH_IMAGES, PATH_PORTFOLIOS_OWNED)
			.authenticated()
			.requestMatchers(HttpMethod.DELETE, PATH_OFFERS, PATH_IMAGES, PATH_PORTFOLIOS_OWNED)
			.authenticated().requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
				"/swagger-ui.html")
			.anonymous())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
		.headers(headers -> headers.contentSecurityPolicy(csp -> csp.policyDirectives(
			"default-src 'self'; script-src 'self' https://cdn.jsdelivr.net; object-src 'none'; frame-ancestors 'none';"))
			.frameOptions(frame -> frame.deny()) // anti-clickjacking
			.contentTypeOptions(Customizer.withDefaults()))
		.build();
    }

    @Bean
    @Profile("prod")
    SecurityFilterChain prodFilterChain(HttpSecurity http) throws Exception {
	return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
		.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
		.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET, "/offers/owned", PATH_PORTFOLIOS_OWNED).authenticated()
		.requestMatchers(HttpMethod.GET, PATH_OFFERS, "/api/**", PATH_IMAGES, "/portfolios/**").permitAll()
		.requestMatchers(HttpMethod.POST, "/offers", PATH_IMAGES, PATH_PORTFOLIOS_OWNED).authenticated()
		.requestMatchers(HttpMethod.PATCH, PATH_OFFERS, PATH_IMAGES, PATH_PORTFOLIOS_OWNED).authenticated()
		.requestMatchers(HttpMethod.DELETE, PATH_OFFERS, PATH_IMAGES, PATH_PORTFOLIOS_OWNED).authenticated())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
		.headers(headers -> headers.contentSecurityPolicy(csp -> csp.policyDirectives(
			"default-src 'self'; script-src 'self' https://cdn.jsdelivr.net; object-src 'none'; frame-ancestors 'none';"))
			.frameOptions(frame -> frame.deny()) // anti-clickjacking
			.contentTypeOptions(Customizer.withDefaults()))
		.build();
    }

    @Bean
    @Profile("!prod")
    CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowedOrigins(List.of(allowedOrigins));
	config.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
	config.setAllowedHeaders(List.of("*"));
	config.setAllowCredentials(true);

	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", config);
	return source;
    }

    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }
}
