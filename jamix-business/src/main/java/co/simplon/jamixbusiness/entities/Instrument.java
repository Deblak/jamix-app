package co.simplon.jamixbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_instruments")
public class Instrument extends AbstractEntity {

    @Column(name = "instrument_name")
    private String name;

    public Instrument() {
    }

    public Instrument(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
