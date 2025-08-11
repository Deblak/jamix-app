package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.Size;

public record OfferSearchDto(@Size(max = 200) @NoXSS(message = "HTML tags are not allowed") String title,

	Long locationId, Long instrumentId, Long styleId, Long goalId,

	@Size(max = 50) @NoXSS(message = "Invalid city format") String city,

	@Size(max = 5) @NoXSS(message = "Invalid zip code format") String zipCode,

	LocalDate postedAfter,

	String sortDirection) {

}
