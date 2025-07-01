package br.com.victorcosta.libraryapi.modules.book.dto;

import br.com.victorcosta.libraryapi.modules.book.domain.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBookRequestDto(
        String title,
        String isbn,
        String publicationYear,
        Integer numberOfPages,
        String edition,
        BookCategory bookCategory,
        UUID authorId
) {
}
