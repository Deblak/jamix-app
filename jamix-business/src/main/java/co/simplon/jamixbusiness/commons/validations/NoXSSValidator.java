package co.simplon.jamixbusiness.commons.validations;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoXSSValidator implements ConstraintValidator<NoXSS, String> {

    private final PolicyFactory policy = new HtmlPolicyBuilder().toFactory();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	String sanitized = policy.sanitize(value);
	return sanitized.equals(value);
    }
}
