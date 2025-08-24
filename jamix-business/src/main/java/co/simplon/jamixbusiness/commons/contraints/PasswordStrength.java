package co.simplon.jamixbusiness.commons.contraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.auth0.jwt.interfaces.Payload;

import co.simplon.jamixbusiness.commons.contraints.validators.PasswordValidator;
import jakarta.validation.Constraint;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordStrength {

    String message() default "Password strong is not enough";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
