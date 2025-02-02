package co.simplon.jamixbusiness.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_offers")
public class Offer extends AbstractEntity {
    @Column(name = "offer_title", nullable = false)
    private String title;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "offer_img")
    private String imageId;
    @ManyToOne
    @JoinColumn(name = "id_instrument")
    private Instrument instrument;
    @ManyToOne
    @JoinColumn(name = "id_style")
    private Style style;
    @ManyToOne
    @JoinColumn(name = "id_goal")
    private Goal goal;
    @Column(name = "offer_desc")
    private String description;
    @Column(name = "contact_email")
    private String contactMail;
    @Column(name = "offer_create_date", updatable = false, insertable = false)
    private LocalDate createdAt;
    @ManyToOne
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

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
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

    @SuppressWarnings("unused")
    public void setCreatedAt(LocalDate createdAt) {
	// handle by DB
	this.createdAt = createdAt;
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

    public String getImageId() {
	return imageId;
    }

    public void setImageId(String imageId) {
	this.imageId = imageId;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
    }

    @Override
    public String toString() {
	return "Offer [title=" + title + ", city=" + city + ", zipCode=" + zipCode + ", imageId=" + imageId
		+ ", instrument=" + instrument + ", style=" + style + ", goal=" + goal + ", description=" + description
		+ ", contactMail=" + contactMail + ", createdAt=" + createdAt + "]";
    }

}
