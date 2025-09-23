package br.com.victorcosta.libraryapi.providers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IndustryIdentifier(
        String type,
        String identifier
) {
}
