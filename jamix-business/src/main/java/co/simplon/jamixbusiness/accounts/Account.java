package co.simplon.jamixbusiness.accounts;
import java.util.Objects;

import co.simplon.jamixbusiness.commons.AbstractEntity;
import co.simplon.jamixbusiness.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public class Account extends AbstractEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER) // fetch for lazy loading (see toString)
    @JoinColumn(name = "id_role")
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

    public Account(String username, String email, String password, Role role) {
	Objects.requireNonNull(username);
	Objects.requireNonNull(email);
	Objects.requireNonNull(password);
	Objects.requireNonNull(role);
	this.username = username;
	this.email = email;
	this.password = password;
	this.role = role;
    }

    @Override
    public String toString() {
	return "UserAccount [username=" + username + ", email=" + email + ", password=[REDACTED]"
		+ ", role= LAZY_LOADED]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(username, email, role);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Account)) {
	    return false;
	}
	return obj instanceof Account other && email.equals(other.email);
    }
}
