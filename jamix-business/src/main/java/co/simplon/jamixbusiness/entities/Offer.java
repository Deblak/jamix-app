package co.simplon.jamixbusiness.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offer")
    private Long id;

    @Column(name = "offer_title")
    private String title;

    @Column(name = "offer_desc")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "contact_email")
    private String mail;

    // @CreationTimestamp
    @Column(name = "offer_create_date", updatable = false, insertable = false)
    // @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate createdAt;

    public Offer() {
	// Default for ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
	// handle by DB
	this.id = id;
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

    public LocalDate getCreatedAt() {
	return createdAt;
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

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    @SuppressWarnings("unused")
    public void setCreatedAt(LocalDate createdAt) {
	// handle by DB
	this.createdAt = createdAt;
    }

    @Override
    public String toString() {
	return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", city=" + city + ", zipCode="
		+ zipCode + ", mail=" + mail + ", createdAt=" + createdAt + "]";
    }

}
