package co.simplon.jamixbusiness.dtos;

import java.time.LocalDate;

public record AdView(Long id, String title, String description, LocalDate created_at) {

}
