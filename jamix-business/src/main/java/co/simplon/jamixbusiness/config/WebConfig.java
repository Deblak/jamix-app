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

    @Bean
    @Profile("!prod")
    SecurityFilterChain devFilterChain(HttpSecurity http) throws Exception {
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
			.requestMatchers(HttpMethod.GET, "/offers/owned", "/portfolios/owned").authenticated()
			.requestMatchers(HttpMethod.GET, "/offers/**", "/api/**", "/images/**", "/portfolios/**")
			.permitAll().requestMatchers(HttpMethod.POST, "/offers/contact/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/offers", "/portfolios/owned", "/images/**").authenticated()
			.requestMatchers(HttpMethod.PATCH, "/offers/**", "/images/**", "/portfolios/owned")
			.authenticated()
			.requestMatchers(HttpMethod.DELETE, "/offers/**", "/images/**", "/portfolios/owned")
			.authenticated().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
			.anonymous())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
    }

    @Bean
    @Profile("prod")
    SecurityFilterChain prodFilterChain(HttpSecurity http) throws Exception {
	return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
		.requestMatchers(HttpMethod.POST, "/account/signup", "/account/login").anonymous()
		.requestMatchers(HttpMethod.GET, "/offers/owned", "/portfolios/owned").authenticated()
		.requestMatchers(HttpMethod.GET, "/offers/**", "/api/**", "/images/**", "/portfolios/**").permitAll()
		.requestMatchers(HttpMethod.POST, "/offers", "/images/**", "/portfolios/owned").authenticated()
		.requestMatchers(HttpMethod.PATCH, "/offers/**", "/images/**", "/portfolios/owned").authenticated()
		.requestMatchers(HttpMethod.DELETE, "/offers/**", "/images/**", "/portfolios/owned").authenticated())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
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
