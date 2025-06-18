package br.com.victorcosta.libraryapi.modules.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank
        String fullName,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String email
) {
}