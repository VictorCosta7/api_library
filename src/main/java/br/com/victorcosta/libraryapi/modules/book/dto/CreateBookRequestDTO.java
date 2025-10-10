package br.com.victorcosta.libraryapi.modules.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateBookRequestDTO(
        @NotBlank(message = "ISBN is required.")
        @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
                message = "Invalid ISBN format. Must be a valid 10 or 13-digit ISBN.")
        String isbn,

        @NotNull(message = "Quantity is required.")
        @Min(value = 1, message = "Quantity must be at least 1.")
        Integer quantity
) {}
