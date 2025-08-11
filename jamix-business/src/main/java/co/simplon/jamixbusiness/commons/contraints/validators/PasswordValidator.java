package co.simplon.jamixbusiness.commons.contraints.validators;

import co.simplon.jamixbusiness.commons.contraints.PasswordStrength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordStrength, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#?!@$%^&*\\-]).{12,72}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
	if (password == null) {
	    return false;
	}
	return password.matches(PASSWORD_PATTERN);
    }
}
