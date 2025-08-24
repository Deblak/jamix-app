package co.simplon.jamixbusiness.portfolios.entities;

import java.util.Objects;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.commons.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_portfolios")
public class Portfolio extends AbstractEntity {

    @Column(name = "band_name", nullable = false)
    private String bandName;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "biography")
    private String biography;

    @Column(name = "portfolio_img")
    private String imageId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    public Portfolio() {
	// Default for ORM
    }

    public String getBandName() {
	return bandName;
    }

    public void setBandName(String bandName) {
	this.bandName = bandName;
    }

    public String getBiography() {
	return biography;
    }

    public void setBiography(String biography) {
	this.biography = biography;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
    }

    public String getTagline() {
	return tagline;
    }

    public void setTagline(String tagline) {
	this.tagline = tagline;
    }

    @Override
    public String toString() {
	return "Portfolio [bandName=" + bandName + ", tagline=" + tagline + ", biography=" + biography + ", imageId="
		+ imageId + ", account= LAZY_LOADED]";
    }

    public String getImageId() {
	return imageId;
    }

    protected Portfolio(String bandName, String tagline, String biography, String imageId, Account account) {
	this.bandName = Objects.requireNonNull(bandName, "band name is required");
	this.tagline = tagline;
	this.biography = biography;
	this.imageId = imageId;
	this.account = Objects.requireNonNull(account, "an account is required");
    }

    public void setImageId(String imageId) {
	this.imageId = imageId;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Portfolio that)) {
	    return false;
	}
	return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
	return Objects.hash(getId());
    }

}
