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

    // @CreationTimestamp
    @Column(name = "offer_create_date", updatable = false, insertable = false)
    private LocalDate created_at;

    public Offer() {
	// Default for ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
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

    public LocalDate getCreated_at() {
	return created_at;
    }

    @SuppressWarnings("unused")
    public void setCreated_at(LocalDate created_at) {
	// handle by DB
	this.created_at = created_at;
    }

    @Override
    public String toString() {
	return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", created_at=" + created_at
		+ "]";
    }

}
