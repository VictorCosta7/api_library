package br.com.victorcosta.libraryapi.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthUserDto(
        @NotBlank(message = "Password is required.")
        @Size(min = 6, message = "Password must be at least 6 characters long.")
        String password,

        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email format.")
        String email
) {
}
