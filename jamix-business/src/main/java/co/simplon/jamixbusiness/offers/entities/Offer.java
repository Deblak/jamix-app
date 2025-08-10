package co.simplon.jamixbusiness.offers.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.commons.AbstractEntity;
import co.simplon.jamixbusiness.locations.Location;
import co.simplon.jamixbusiness.preferences.Goal;
import co.simplon.jamixbusiness.preferences.Instrument;
import co.simplon.jamixbusiness.preferences.Style;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_offers")
public class Offer extends AbstractEntity {
    @Column(name = "offer_title", nullable = false)
    private String title;
    @Column(name = "offer_desc")
    private String description;
    @Column(name = "contact_email")
    private String contactMail;
    @CreatedDate
    @Column(name = "offer_create_date", nullable = false, updatable = false)
    private LocalDate createdAt;
    @Column(name = "offer_img")
    private String imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instrument")
    private Instrument instrument;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_style")
    private Style style;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_goal")
    private Goal goal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    @JsonManagedReference
    private Account account;

    public Offer() {
	// Default for ORM
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getContactMail() {
	return contactMail;
    }

    public void setContactMail(String contactMail) {
	this.contactMail = contactMail;
    }

    public LocalDate getCreatedAt() {
	return createdAt;
    }

    public String getImageId() {
	return imageId;
    }

    public void setImageId(String imageId) {
	this.imageId = imageId;
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public Instrument getInstrument() {
	return instrument;
    }

    public void setInstrument(Instrument instrument) {
	this.instrument = instrument;
    }

    public Style getStyle() {
	return style;
    }

    public void setStyle(Style style) {
	this.style = style;
    }

    public Goal getGoal() {
	return goal;
    }

    public void setGoal(Goal goal) {
	this.goal = goal;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
    }

    @Override
    public String toString() {
	return "Offer [title=" + title + ", description=" + description + ", contactMail=[REDACTED]" + ", createdAt="
		+ createdAt + ", imageId=" + imageId + ", location=" + location.getId() + ", instrument="
		+ instrument.getId() + ", style=" + style.getId() + ", goal=" + goal.getId() + ", account="
		+ account.getId() + "]";
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Offer)) {
	    return false;
	}
	Offer other = (Offer) obj;
	return Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
	return Objects.hash(getId());
    }

}
