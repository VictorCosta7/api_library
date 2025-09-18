package br.com.victorcosta.libraryapi.modules.rental.dto;

import java.time.LocalDateTime;

public record RegisterRentalDto(
    LocalDateTime rentalDate
) {
}
