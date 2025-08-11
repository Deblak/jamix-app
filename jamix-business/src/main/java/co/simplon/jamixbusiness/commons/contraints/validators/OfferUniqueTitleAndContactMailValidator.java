package co.simplon.jamixbusiness.commons.contraints.validators;

import co.simplon.jamixbusiness.commons.contraints.OfferUniqueTitleAndContactMail;
import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OfferUniqueTitleAndContactMailValidator
	implements ConstraintValidator<OfferUniqueTitleAndContactMail, OfferCreateDto> {

    private final OfferRepository repository;

    protected OfferUniqueTitleAndContactMailValidator(OfferRepository repository) {
	this.repository = repository;
    }

    @Override
    public boolean isValid(OfferCreateDto value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	String title = value.title();
	String contactMail = value.contactMail();

	if (title == null || contactMail == null) {
	    return true;
	}
	boolean exists = repository.existsByTitleIgnoreCaseAndContactMail(title, contactMail);

	if (exists) {
	    context.disableDefaultConstraintViolation();
	    context.buildConstraintViolationWithTemplate("An offer with this title and contact email already exists")
		    .addPropertyNode("title").addConstraintViolation();
	    return false;
	}
	return true;
    }

}
