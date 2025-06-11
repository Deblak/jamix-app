package co.simplon.jamixbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("!prod")
public class WebConfig {
    @Value("${co.simplon.jamix.cors}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("GET", "POST", "PATCH",
			"DELETE");
	    }
	};
    }

    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }
}
