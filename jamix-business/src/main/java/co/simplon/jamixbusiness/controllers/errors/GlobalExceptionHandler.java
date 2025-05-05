package co.simplon.jamixbusiness.controllers.errors;

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
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
//	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
//	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception ex) {
//	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    protected ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException ex,
//	    WebRequest request) {
//	return ResponseEntity.status(HttpStatus.CONFLICT).body("Data integrity violation: " + ex.getMessage());
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//	    HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//	List<FieldError> errors = ex.getFieldErrors();
//	CustomErrors customErrors = new CustomErrors();
//	for (FieldError error : errors) {
//	    String fieldName = error.getField();
//	    String code = error.getCode();
//	    customErrors.addFieldError(fieldName, code);
//	}
//	List<ObjectError> globalErrors = ex.getGlobalErrors();
//	for (ObjectError error : globalErrors) {
//	    String code = error.getCode();
//	    customErrors.addGlobalError(code);
//	}
//	return handleExceptionInternal(ex, customErrors, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//	    HttpStatusCode statusCode, WebRequest request) {
//	return super.handleExceptionInternal(ex, body, headers, statusCode, request);
//    }
//
//    /**
//     * Handle ResponseStatusException and wrap into CustomErrors.
//     */
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<CustomErrors> handleResponseStatusException(ResponseStatusException ex) {
//	CustomErrors errors = new CustomErrors();
//	// use the exception reason as a global error code/message
//	errors.addGlobalError(ex.getReason());
//	return new ResponseEntity<>(errors, ex.getStatusCode());
//    }
    // On remplace handleValidation par un override de
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

    // 2 On garde le handler pour ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomErrors> handleResponseStatus(ResponseStatusException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError(ex.getReason());
	return ResponseEntity.status(ex.getStatusCode()).body(errors);
    }

    // 3 Et votre handler BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrors> handleBadCredentials(BadCredentialsException ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError(ex.getMessage());
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    // 4 Le fallback pour tout le reste
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrors> handleAll(Exception ex) {
	CustomErrors errors = new CustomErrors();
	errors.addGlobalError("Internal server error");
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

}
