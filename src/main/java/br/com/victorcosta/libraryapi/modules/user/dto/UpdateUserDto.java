package br.com.victorcosta.libraryapi.modules.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @Nullable
        @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters.")
        String fullName,

        @Nullable
        @Size(min = 3, max = 50, message = "Username must between 3 and 50 characters.")
        String username,

        @Nullable
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        String password,

        @Nullable
        @Email(message = "Invalid email format.")
        String email
) {
}
