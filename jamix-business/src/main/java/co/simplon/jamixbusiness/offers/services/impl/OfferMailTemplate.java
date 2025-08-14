package co.simplon.jamixbusiness.offers.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

@Component
public class OfferMailTemplate {

    public String contactSubject(String offerTitle) {
	return "Name: " + offerTitle;
    }

    public String contactTemplate(String visitorName, String visitorMail, String visitorMessage, String offerTitle) {
	try (InputStream inputStream = getClass().getClassLoader()
		.getResourceAsStream("templates/email-contact-offer.html")) {

	    String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
	    return template.replace("${visitorName}", visitorName).replace("${visitorMail}", visitorMail)
		    .replace("${visitorMessage}", visitorMessage).replace("${offerTitle}", offerTitle);

	} catch (IOException exception) {
	    throw new RuntimeException("Failed to load contact email template", exception);
	}
    }
}
