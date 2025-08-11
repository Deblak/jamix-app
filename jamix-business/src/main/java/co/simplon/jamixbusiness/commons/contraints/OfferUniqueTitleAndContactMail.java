package co.simplon.jamixbusiness.commons.contraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.simplon.jamixbusiness.commons.contraints.validators.OfferUniqueTitleAndContactMailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = OfferUniqueTitleAndContactMailValidator.class)
public @interface OfferUniqueTitleAndContactMail {
    String message() default "An offer with this title and contactMail already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
