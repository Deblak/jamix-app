package co.simplon.jamixbusiness.accounts.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.accounts.dtos.AccountInfo;
import co.simplon.jamixbusiness.accounts.repositories.AccountRepository;
import co.simplon.jamixbusiness.accounts.services.AccountAdminService;

@Service
public class AccountAdminServiceImpl implements AccountAdminService {

    private final AccountRepository repository;

    protected AccountAdminServiceImpl(AccountRepository repository) {
	this.repository = repository;
    }

    @Override
    public List<AccountInfo> getAllAccounts() {
	return repository.findAllByOrderByEmailAsc().stream()
		.map(account -> new AccountInfo(account.getId(), account.getUsername(), account.getEmail())).toList();
    }

    @Override
    public void deleteById(Long id) {
	repository.deleteById(id);
    }

}
