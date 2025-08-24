package co.simplon.jamixbusiness.commons.contraints.validators;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.commons.contraints.FileSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private long max;

    @Override
    public void initialize(FileSize annotation) {
	long value = annotation.max();
	if (value <= 0) {
	    throw new IllegalArgumentException("value must be positive: %s".formatted(value));
	}
	max = value;
    }

    @Override
    public boolean isValid(MultipartFile fileValue, ConstraintValidatorContext context) {
	if (fileValue == null) {
	    return true;
	}
	return fileValue.getSize() <= max;
    }

}
