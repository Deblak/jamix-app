package co.simplon.jamixbusiness.commons.contraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.simplon.jamixbusiness.commons.contraints.validators.PortfolioUniqueBandNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = PortfolioUniqueBandNameValidator.class)
public @interface PortfolioUniqueBandName {
    String message() default "Band name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
