package co.simplon.jamixbusiness.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.dtos.UserCreate;
import co.simplon.jamixbusiness.dtos.UserLogIn;
import co.simplon.jamixbusiness.services.UserService;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    private final UserService service;

    protected UserAccountController(UserService service) {
	this.service = service;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserCreate inputs) {
	service.create(inputs);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    Object authentificated(@RequestBody UserLogIn inputs) {
	return service.authenticated(inputs);
    }
}
