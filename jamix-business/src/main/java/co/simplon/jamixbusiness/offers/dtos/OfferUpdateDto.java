package co.simplon.jamixbusiness.offers.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.contraints.FileSize;
import co.simplon.jamixbusiness.commons.contraints.FileType;
import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OfferUpdateDto(

	@Size(min = 1, max = 200) @NoXSS(message = "HTML tags are not allowed") String title,

	@Size(min = 1, max = 600) @NoXSS(message = "HTML tags are not allowed") String description,

	@Size(min = 6, max = 255) @Email(message = "Invalid email format") String contactMail,

	@Size(min = 1, max = 50) @NoXSS(message = "Invalid city format") String city,

	@Size(min = 5, max = 5) @NoXSS(message = "Invalid zip code format") String zipCode,

	@Positive Long instrumentId,

	@Positive Long styleId,

	@Positive Long goalId,

	@FileType(types = MediaType.IMAGE_JPEG_VALUE) @FileSize(max = FileSize.FIVE_MB) MultipartFile image) {

}
