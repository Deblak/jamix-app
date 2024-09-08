package co.simplon.jamixbusiness.dtos;

import jakarta.validation.constraints.Size;

public record AdUpdateDto(@Size(max = 200) String title, @Size(max = 600) String description) {

}
