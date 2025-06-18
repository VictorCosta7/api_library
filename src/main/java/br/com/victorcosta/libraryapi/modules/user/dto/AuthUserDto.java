package br.com.victorcosta.libraryapi.modules.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDto(
        @NotBlank
        String password,

        @NotBlank
        String email
) {
}
