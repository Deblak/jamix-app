package co.simplon.jamixbusiness.commons.contraints.validators;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.contraints.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {

    private String[] types;

    @Override
    public void initialize(FileType annotation) {
	types = annotation.types();
    }

    @Override
    public boolean isValid(MultipartFile fileValue, ConstraintValidatorContext context) {
	if (fileValue == null) {
	    return true;
	}
	String contentType = fileValue.getContentType();
	return Arrays.stream(types).anyMatch((type) -> type.equals(MediaType.ALL_VALUE) || type.equals(contentType));
    }

}
