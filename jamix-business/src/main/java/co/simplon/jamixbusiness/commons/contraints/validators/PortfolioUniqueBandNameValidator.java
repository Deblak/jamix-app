package co.simplon.jamixbusiness.commons.contraints.validators;

import co.simplon.jamixbusiness.commons.contraints.PortfolioUniqueBandName;
import co.simplon.jamixbusiness.portfolios.repositories.PortfolioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public final class PortfolioUniqueBandNameValidator implements ConstraintValidator<PortfolioUniqueBandName, String> {

    private final PortfolioRepository repository;

    protected PortfolioUniqueBandNameValidator(PortfolioRepository repository) {
	this.repository = repository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return !repository.existsByBandName(value);
    }

}
