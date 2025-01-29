package co.simplon.jamixbusiness.entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_images")
public class Image extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "image_data", columnDefinition = "bytea")
    private byte[] imageData;

    public Image() {
	// Default for ORM
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public byte[] getImageData() {
	return imageData;
    }

    public void setImageData(byte[] imageData) {
	this.imageData = imageData;
    }

    @Override
    public String toString() {
	return "Image [ type=" + type + ", imageData=" + Arrays.toString(imageData) + "]";
    }

}
