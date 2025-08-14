package co.simplon.jamixbusiness.portfolios.dtos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.contraints.FileSize;
import co.simplon.jamixbusiness.commons.contraints.FileType;
import co.simplon.jamixbusiness.commons.contraints.NoXSS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PortfolioCreateDto(
	@NotBlank(message = "Band name is required") @Size(min = 1, max = 50, message = "Band name must not exceed 50 characters") @NoXSS(message = "HTML tags are not allowed") String bandName,

	@Size(max = 120, message = "Tagline must not exceed 120 characters") @NoXSS(message = "HTML tags are not allowed") String tagline,

	@Size(max = 800, message = "Biography must not exceed 800 characters") @NoXSS(message = "HTML tags are not allowed") String biography,

	@FileType(types = {
		MediaType.IMAGE_JPEG_VALUE }) @FileSize(max = FileSize.FIVE_MB) MultipartFile image){
}
