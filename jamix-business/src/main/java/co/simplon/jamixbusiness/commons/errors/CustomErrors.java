package co.simplon.jamixbusiness.commons.errors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomErrors {
    private final Map<String, Collection<String>> fieldErrors = new HashMap<>();
    private final Collection<String> globalErrors = new ArrayList<>();

    public CustomErrors() {
	// ORM
    }

    public void addFieldError(String fieldName, String errorCode) {
	Collection<String> codes = fieldErrors.get(fieldName);
	if (codes == null) {
	    codes = new ArrayList<String>();
	    fieldErrors.put(fieldName, codes);
	}
	codes.add(errorCode);
    }

    public Map<String, Collection<String>> getFieldErrors() {
	return Collections.unmodifiableMap(fieldErrors);
    }

    public void addGlobalError(String code) {
	globalErrors.add(code);
    }

    public Collection<String> getGlobalErrors() {
	return Collections.unmodifiableCollection(globalErrors);
    }

    @Override
    public String toString() {
	return "{fieldErrors=%s, globalErrors=%s}".formatted(fieldErrors, globalErrors);
    }

}
