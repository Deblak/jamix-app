package co.simplon.jamixbusiness.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl service;

    protected AccountController(AccountServiceImpl service) {
	this.service = service;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody AccountCreateDto inputs) {
	service.create(inputs);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AccountLoginDto inputs) {
	LoginResponse response = service.authenticated(inputs);
	return ResponseEntity.ok(response);
    }

    @DeleteMapping("/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@RequestParam String email) {
	service.deleteByEmail(email);
    }
}
