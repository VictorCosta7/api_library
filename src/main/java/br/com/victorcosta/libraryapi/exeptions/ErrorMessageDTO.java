package br.com.victorcosta.libraryapi.exeptions;

public record ErrorMessageDTO(
        String message,
        String error
) {}
