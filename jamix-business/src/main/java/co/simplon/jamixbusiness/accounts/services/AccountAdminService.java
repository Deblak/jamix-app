package co.simplon.jamixbusiness.accounts.services;

import java.util.List;

import co.simplon.jamixbusiness.accounts.dtos.AccountInfo;

public interface AccountAdminService {
    List<AccountInfo> getAllAccounts();

    void deleteById(Long id);
}
