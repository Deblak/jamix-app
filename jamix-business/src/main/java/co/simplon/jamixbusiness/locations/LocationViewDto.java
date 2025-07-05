package co.simplon.jamixbusiness.locations;

import jakarta.validation.constraints.Positive;

public record LocationViewDto(@Positive Long id, String city, String zipCode) {

}
