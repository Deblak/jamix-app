package co.simplon.jamixbusiness.commons.contraints.validators;

import co.simplon.jamixbusiness.accounts.AccountRepository;
import co.simplon.jamixbusiness.commons.contraints.AccountUniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public final class AccountUniqueEmailValidator implements ConstraintValidator<AccountUniqueEmail, String> {

    private final AccountRepository repository;

    protected AccountUniqueEmailValidator(AccountRepository repository) {
	this.repository = repository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return !repository.existsByEmail(value);
    }

}
