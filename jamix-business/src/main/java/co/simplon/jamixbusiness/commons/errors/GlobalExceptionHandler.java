package co.simplon.jamixbusiness.commons.errors;

import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleMethodArgumentNotValid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, org.springframework.http.HttpStatusCode status, WebRequest request) {

	CustomErrors errors = new CustomErrors();
	for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
	    errors.addFieldError(fe.getField(), fe.getCode());
	}
	ex.getBindingResult().getGlobalErrors().forEach(ge -> errors.addGlobalError(ge.getDefaultMessage()));

	return ResponseEntity.badRequest().body(errors);
    }

    // ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomErrors> handleResponseStatus(ResponseStatusException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError(ex.getReason());
	return ResponseEntity.status(ex.getStatusCode()).body(errors);
    }

    // BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrors> handleBadCredentials(BadCredentialsException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError(ex.getMessage());
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    // Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrors> handleAll(Exception ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError("Internal server error");
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CustomErrors> handleDataAccessException(DataAccessException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError("Data access conflict: " + ex.getMessage());
	return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrors> handleNotFoundException(NotFoundException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError(ex.getMessage() != null ? ex.getMessage() : "Object not found");
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

}
