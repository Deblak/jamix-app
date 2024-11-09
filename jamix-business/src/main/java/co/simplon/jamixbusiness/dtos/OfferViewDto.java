package co.simplon.jamixbusiness.dtos;

import java.time.LocalDate;

public record OfferViewDto(Long id, String title, String description, LocalDate created_at) {

}
