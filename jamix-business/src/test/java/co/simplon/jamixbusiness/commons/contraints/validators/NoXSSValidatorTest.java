package co.simplon.jamixbusiness.commons.contraints.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoXSSValidatorTest {
    private NoXSSValidator validator;

    @BeforeEach
    void setUp() {
	validator = new NoXSSValidator();
	validator.initialize(null);
    }

    @Test
    void input_should_be_valid() {
	assertTrue(validator.isValid("Cherche bassiste", null));
    }

    @Test
    void alert_should_not_be_valid() {
	assertFalse(validator.isValid("<script>alert(1)</script>", null));
    }

}
