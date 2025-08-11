package co.simplon.jamixbusiness.commons.contraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.simplon.jamixbusiness.commons.contraints.validators.FileSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {

    static final long ONE_MB = 1024 * 1024;
    static final long TWO_MB = ONE_MB * 2;
    static final long FIVE_MB = ONE_MB * 5;

    long max() default FileSize.ONE_MB;

    String message() default "Invalid image file : verify size";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
