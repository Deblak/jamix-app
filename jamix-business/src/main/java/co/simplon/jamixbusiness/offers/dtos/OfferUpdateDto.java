package co.simplon.jamixbusiness.offers.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.validations.FileSize;
import co.simplon.jamixbusiness.commons.validations.FileType;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OfferUpdateDto(

	@Size(max = 200) String title,

	@Size(max = 600) String description,

	@Size(max = 255) String contactMail,

	@Size(max = 50) String city,

	@Size(max = 5) String zipCode,

	@Positive Long instrumentId,

	@Positive Long styleId,

	@Positive Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.TWO_MB) MultipartFile image){

}
