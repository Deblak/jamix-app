package co.simplon.jamixbusiness.offers.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.validations.FileSize;
import co.simplon.jamixbusiness.commons.validations.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OfferCreateDto(@NotBlank @Size(max = 200) String title,

	@NotBlank @Size(max = 600) String description,

	@Size(max = 255) String contactMail,

	@NotBlank @Size(max = 50) String city,

	@NotBlank @Size(max = 5) String zipCode,

	@NotNull @Positive Long instrumentId,

	@NotNull @Positive Long styleId,

	@NotNull @Positive Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.TWO_MB) MultipartFile image){
}
