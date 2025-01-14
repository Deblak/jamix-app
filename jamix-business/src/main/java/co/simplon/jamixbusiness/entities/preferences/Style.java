package co.simplon.jamixbusiness.entities.preferences;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_styles")
public class Style {
    @Id
    @Column(name = "style_name")
    private String name;

    public Style() {
    }

    public Style(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
