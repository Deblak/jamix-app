package co.simplon.jamixbusiness.entities;

import java.time.LocalDate;

import co.simplon.jamixbusiness.entities.preferences.Goal;
import co.simplon.jamixbusiness.entities.preferences.Instrument;
import co.simplon.jamixbusiness.entities.preferences.Style;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_offers")
public class Offer extends AbstractEntity {

    @Column(name = "offer_title", nullable = false)
    private String title;

    @Column(name = "offer_desc")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "contact_email")
    private String mail;

    public Image getImage() {
	return image;
    }

    public void setImage(Image image) {
	this.image = image;
    }

    @Column(name = "offer_create_date", updatable = false, insertable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "id_instrument")
    private Instrument instrument;

    @ManyToOne
    @JoinColumn(name = "id_style")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "id_goal")
    private Goal goal;

    @OneToOne
    @JoinColumn(name = "id_image")
    private Image image;

    public Offer() {
	// Default for ORM
    }

    protected Offer(String title, String description, String city, String zipCode, String mail, LocalDate createdAt,
	    Instrument instrument, Style style, Goal goal, Image image) {
	super();
	this.title = title;
	this.description = description;
	this.city = city;
	this.zipCode = zipCode;
	this.mail = mail;
	this.createdAt = createdAt;
	this.instrument = instrument;
	this.style = style;
	this.goal = goal;
	this.image = image;
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

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
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

    @Override
    public String toString() {
	return "Offer [title=" + title + ", description=" + description + ", city=" + city + ", zipCode=" + zipCode
		+ ", mail=" + mail + ", createdAt=" + createdAt + ", instrument=" + instrument + ", style=" + style
		+ ", goal=" + goal + ", image=" + image + "]";
    }

}
