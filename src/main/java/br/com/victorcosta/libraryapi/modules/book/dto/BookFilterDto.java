package br.com.victorcosta.libraryapi.modules.book.dto;

public record BookFilterDto(
        String author,
        String category,
        String title
){}
