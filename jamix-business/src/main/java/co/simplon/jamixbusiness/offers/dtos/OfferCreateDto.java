package co.simplon.jamixbusiness.offers.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.contraints.FileSize;
import co.simplon.jamixbusiness.commons.contraints.FileType;
import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import co.simplon.jamixbusiness.commons.contraints.OfferUniqueTitleAndContactMail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@OfferUniqueTitleAndContactMail
public record OfferCreateDto(
	@NotBlank @Size(min = 1, max = 200) @NoXSS(message = "HTML tags are not allowed") String title,

	@NotBlank @Size(min = 1, max = 600) @NoXSS(message = "HTML tags are not allowed") String description,

	@NotBlank @Size(min = 6, max = 255) @Email(message = "Invalid email format") String contactMail,

	@NotBlank @Size(min = 1, max = 50) @NoXSS(message = "Invalid city format") String city,

	@NotBlank @Size(min = 5, max = 5) @Pattern(regexp = "\\d{5}", message = "Zip code must be 5 digits") @NoXSS(message = "Invalid zip code format") String zipCode,

	@NotNull @Positive Long instrumentId,

	@NotNull @Positive Long styleId,

	@NotNull @Positive Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.FIVE_MB) MultipartFile image){
}
