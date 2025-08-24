package co.simplon.jamixbusiness.accounts;

import co.simplon.jamixbusiness.commons.AbstractEntity;
import co.simplon.jamixbusiness.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "t_accounts")
public class Account extends AbstractEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // fetch for lazy loading (see toString)
    @JoinColumn(name = "id_role", updatable = false)
    private Role role;

    public Account() {
	// Default for ORM
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    @Override
    public String toString() {
	return "UserAccount [username=" + username + ", email==[REDACTED]" + ", password=[REDACTED]"
		+ ", role= [LAZY_LOADED]]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Account account && Objects.equals(email, account.email);
    }
}
