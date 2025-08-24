package co.simplon.jamixbusiness.commons.contraints.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class NoXSSValidatorTest {
    private NoXSSValidator validator;

    @BeforeEach
    void setUp() {
	validator = new NoXSSValidator();
	validator.initialize(null);
    }

    @Test
    void input_should_be_valid() {
	assertTrue(validator.isValid("This input is valid. I'm happy !", null));
    }

    @ParameterizedTest
    @MethodSource("insertXSSFromTxt")
    void input_should_not_be_valid(String payload) {
	assertFalse(validator.isValid(payload, null));
    }

    static Stream<String> insertXSSFromTxt() throws IOException {
	try (InputStream inputStream = NoXSSValidatorTest.class.getResourceAsStream("/xss-validation-data.txt");
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
	    List<String> lines = new ArrayList<>();
	    String line;
	    while ((line = reader.readLine()) != null) {
		lines.add(line);
	    }
	    return lines.stream();
	}
    }
}
