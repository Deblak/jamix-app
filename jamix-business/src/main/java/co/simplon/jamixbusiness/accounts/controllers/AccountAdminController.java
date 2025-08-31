package co.simplon.jamixbusiness.accounts.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.jamixbusiness.accounts.dtos.AccountInfo;
import co.simplon.jamixbusiness.accounts.services.AccountAdminService;

@RestController
@RequestMapping("/admin")
public class AccountAdminController {
    private final AccountAdminService service;

    public AccountAdminController(AccountAdminService service) {
	this.service = service;
    }

    @GetMapping
    public List<AccountInfo> getAllAccounts() {
	return service.getAllAccounts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@PathVariable Long id) {
	service.deleteById(id);
    }

}
