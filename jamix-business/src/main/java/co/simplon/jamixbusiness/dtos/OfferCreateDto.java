package co.simplon.jamixbusiness.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.validations.FileSize;
import co.simplon.jamixbusiness.validations.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OfferCreateDto(@NotBlank @Size(max = 200) String title, @NotBlank @Size(max = 600) String description,

	@Size(max = 50) String city,

	@Size(max = 5) String zipCode,

	@Size(max = 255) String contactMail,

	@NotNull @Positive Long instrumentId,

	@NotNull @Positive Long styleId,

	@NotNull @Positive Long goalId,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.TWO_MB) MultipartFile image){
}
