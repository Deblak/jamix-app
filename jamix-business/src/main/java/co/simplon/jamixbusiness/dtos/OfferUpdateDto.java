package co.simplon.jamixbusiness.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.validations.FileSize;
import co.simplon.jamixbusiness.validations.FileType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OfferUpdateDto(

	@Size(max = 200) String title,

	@Size(max = 600) String description,

	@Size(max = 50) String city,

	@Size(max = 5) String zipCode,

	@Size(max = 255) String contactMail,

	@NotNull Long instrumentId,

	@NotNull Long styleId,

	@NotNull Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.TWO_MB) MultipartFile image){

}
