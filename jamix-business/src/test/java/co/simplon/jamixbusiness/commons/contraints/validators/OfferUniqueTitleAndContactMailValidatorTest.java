package co.simplon.jamixbusiness.commons.contraints.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.simplon.jamixbusiness.offers.dtos.OfferCreateDto;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import jakarta.validation.ConstraintValidatorContext;

public class OfferUniqueTitleAndContactMailValidatorTest {
    @Test
    void isInvalid_whenDuplicateExists() {
	OfferRepository repository = Mockito.mock(OfferRepository.class);

	OfferUniqueTitleAndContactMailValidator validator = new OfferUniqueTitleAndContactMailValidator(repository);

	OfferCreateDto dto = new OfferCreateDto("My title", "desc", "mail@test.com", "Paris", "75001", 1L, 1L, 1L,
		null);

	when(repository.existsByTitleIgnoreCaseAndContactMail("My title", "mail@test.com")).thenReturn(true);

	ConstraintValidatorContext ctx = Mockito.mock(ConstraintValidatorContext.class, Mockito.RETURNS_DEEP_STUBS);

	boolean valid = validator.isValid(dto, ctx);

	assertFalse(valid);
    }
}
