package co.simplon.jamixbusiness.commons.validations;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CleanXSSValidator implements ConstraintValidator<CleanXSS, String> {

   private final PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	String sanitized = policy.sanitize(value);
	return sanitized.equals(value);
   }
}
