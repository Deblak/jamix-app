package co.simplon.jamixbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    // @OneToMany(mappedBy = "account")
    // @JsonBackReference
    // private Set<Offer> offers;

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

//    public Set<Offer> getOffers() {
//	return offers;
//    }
//
//    public void setOffers(Set<Offer> offers) {
//	this.offers = offers;
//    }

    @Override
    public String toString() {
	return "UserAccount [username=" + username + ", email=" + email + ", password=[REDACTED]" + "]";
    }

}
