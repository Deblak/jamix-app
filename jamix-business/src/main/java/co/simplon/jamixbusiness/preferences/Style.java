package co.simplon.jamixbusiness.preferences;

import co.simplon.jamixbusiness.commons.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_styles")
public class Style extends AbstractEntity {
    @Column(name = "style_name", nullable = false, unique = true, updatable = false)
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
