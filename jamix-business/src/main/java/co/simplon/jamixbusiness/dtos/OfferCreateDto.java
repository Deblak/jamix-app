package co.simplon.jamixbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OfferCreateDto(@NotBlank @Size(max = 200) String title, @NotBlank @Size(max = 600) String description) {

}
