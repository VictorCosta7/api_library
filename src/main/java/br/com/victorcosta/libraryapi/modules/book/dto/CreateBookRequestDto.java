package br.com.victorcosta.libraryapi.modules.book.dto;

import br.com.victorcosta.libraryapi.modules.book.domain.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBookRequestDto(
        @NotBlank
        String title,

        @NotBlank
        String isbn,

        @NotBlank
        String publicationYear,

        @NotNull
        Integer numberOfPages,

        @NotBlank
        String edition,

        @NotBlank
        BookCategory bookCategory,

        @NotNull
        UUID authorId
) {

}
