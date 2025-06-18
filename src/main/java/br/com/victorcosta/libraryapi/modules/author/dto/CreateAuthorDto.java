package br.com.victorcosta.libraryapi.modules.author.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthorDto(
        @NotBlank(message = "Full name is required")
        String fullName,
        
        @NotBlank(message = "Nationality is required")
        String nationality,

        @NotNull(message = "User ID is required")
        UUID userId
) {
}