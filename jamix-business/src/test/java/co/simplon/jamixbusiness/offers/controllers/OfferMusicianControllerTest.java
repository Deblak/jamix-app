package co.simplon.jamixbusiness.offers.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.services.OfferMusicianService;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OfferMusicianControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OfferMusicianService service;

    MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", new byte[0]);

    @Test
    void offer_should_be_created() throws Exception {
	OfferViewDto view = new OfferViewDto(1L, "Test JUnit", "image", "junit@test.mail", 52L, "Poitiers", "86000",
		85L, "Voix", 67L, "Ambiant", 7L, "Entraînment", "Test sans image", LocalDate.parse("2025-06-17"), null);
	when(service.create(any(OfferCreateDto.class), any(MultipartFile.class))).thenReturn(view);

	mockMvc.perform(
		multipart("/offers").file(image).param("title", "Test JUnit").param("description", "Test sans image")
			.param("contactMail", "junit@test.mail").param("city", "Poitiers").param("zipCode", "86000")
			.param("instrumentId", "85").param("styleId", "67").param("goalId", "7"))
		.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.title").value("Test JUnit"));
    }

    /**
     * Test 400 Bad request
     *
     * @param json
     * @throws Exception
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/offer-validation-data.csv", numLinesToSkip = 1, delimiter = '\t')
    void offer_should_not_be_created(String json) throws Exception {
	ObjectMapper mapper = new ObjectMapper();
	JsonNode offer = mapper.readTree(json);
	MockMultipartHttpServletRequestBuilder request = multipart("/offers");
	if (offer.hasNonNull("title")) {
	    request.param("title", offer.get("title").asText());
	}
	if (offer.hasNonNull("description")) {
	    request.param("description", offer.get("description").asText());
	}
	if (offer.hasNonNull("contactMail")) {
	    request.param("contactMail", offer.get("contactMail").asText());
	}
	if (offer.hasNonNull("city")) {
	    request.param("city", offer.get("city").asText());
	}
	if (offer.hasNonNull("zipCode")) {
	    request.param("zipCode", offer.get("zipCode").asText());
	}
	if (offer.hasNonNull("instrumentId")) {
	    request.param("instrumentId", offer.get("instrumentId").asText());
	}
	if (offer.hasNonNull("styleId")) {
	    request.param("styleId", offer.get("styleId").asText());
	}
	if (offer.hasNonNull("goalId")) {
	    request.param("goalId", offer.get("goalId").asText());
	}

	mockMvc.perform(request).andExpect(status().isBadRequest());
    }

}
