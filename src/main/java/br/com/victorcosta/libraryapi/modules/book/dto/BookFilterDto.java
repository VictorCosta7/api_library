package br.com.victorcosta.libraryapi.modules.book.dto;

import java.util.List;

public record BookFilterDto(
        String author,
        String category,
        String title
){}
