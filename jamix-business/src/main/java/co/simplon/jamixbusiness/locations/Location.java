package co.simplon.jamixbusiness.locations;

import co.simplon.jamixbusiness.commons.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_locations")
public class Location extends AbstractEntity {
    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

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

    public Location(String city, String zipCode) {
	this.city = city;
	this.zipCode = zipCode;
    }

    public Location() {
	// for ORM
    }

    @Override
    public String toString() {
	return "Location [city=" + city + ", zipCode=" + zipCode + "]";
    }

}
