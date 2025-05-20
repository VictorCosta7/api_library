package br.com.victorcosta.libraryapi.modules.book.dto;

import lombok.Data;

@Data
public class CreateBookRequestDto {
    private String title;
    private String isbn;
    private String publicationYear;
}
