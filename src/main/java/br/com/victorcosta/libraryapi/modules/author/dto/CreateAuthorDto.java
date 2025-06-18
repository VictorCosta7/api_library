package br.com.victorcosta.libraryapi.modules.author.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthorDto(
        String fullName,
        String nationality,
        UUID userId
) {
}