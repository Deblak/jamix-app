package co.simplon.jamixbusiness.portfolios.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.validations.FileSize;
import co.simplon.jamixbusiness.commons.validations.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PortfolioUpdateDto(
	@NotBlank(message = "Band name is required") @Size(max = 50, message = "Band name must not exceed 50 characters") String bandName,

	@Size(max = 120, message = "Tagline must not exceed 120 characters") String tagline,

	@Size(max = 800, message = "Biography must not exceed 800 characters") String biography,

	@FileType(types = MediaType.IMAGE_JPEG_VALUE) @FileSize(max = FileSize.FIVE_MB) MultipartFile image) {
}
