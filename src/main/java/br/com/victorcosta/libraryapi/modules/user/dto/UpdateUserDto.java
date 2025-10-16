package br.com.victorcosta.libraryapi.modules.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @Nullable
        @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters.")
        String fullName,

        @Nullable
        @Size(min = 3, max = 50, message = "Username must between 3 and 50 characters.")
        String username,

        @Nullable
        @Email(message = "Invalid email format.")
        String email,

        @NotBlank(message = "Telephone is required.")
        @Pattern(
                regexp = "^\\(?(?:[146-9][1-9]|2[1-9]|3[1-5]|5[13-5])\\)?\\s?(?:9\\d{4}|[2-8]\\d{3})\\-?\\d{4}$",
                message = "Invalid phone format. Use (XX) 9XXXX-XXXX or (XX) XXXX-XXXX."
        )
        String phone
) {
}
