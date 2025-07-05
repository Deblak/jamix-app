package co.simplon.jamixbusiness.offers.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.validations.CleanXSS;
import co.simplon.jamixbusiness.commons.validations.FileSize;
import co.simplon.jamixbusiness.commons.validations.FileType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OfferUpdateDto(

	@Size(max = 200) @CleanXSS(message = "Invalid title format") String title,

	@Size(max = 600) @CleanXSS(message = "Invalid description format") String description,

	@Size(max = 255) @Email(message = "Invalid email format") String contactMail,

	@Size(max = 50) @CleanXSS(message = "Invalid city format") String city,

	@Size(max = 5) @CleanXSS(message = "Invalid zip code format") String zipCode,

	@Positive Long instrumentId,

	@Positive Long styleId,

	@Positive Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.TWO_MB) MultipartFile image){

}
