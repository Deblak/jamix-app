package co.simplon.jamixbusiness.security;

import co.simplon.jamixbusiness.accounts.Account;

public interface CurrentUserManager {
    /**
     * @return the Account entity of the currently authenticated user
     * @throws IllegalStateException if no user is authenticated
     */
    Account getCurrentAccount();
}
