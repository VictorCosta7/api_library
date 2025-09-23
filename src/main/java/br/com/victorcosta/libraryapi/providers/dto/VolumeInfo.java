package br.com.victorcosta.libraryapi.providers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VolumeInfo(
        String title,
        List<String> authors,
        String publisher,
        @JsonProperty("publishedDate") String publishedDate,
        String description,
        @JsonProperty("industryIdentifiers") List<IndustryIdentifier> industryIdentifiers,
        @JsonProperty("pageCount") Integer pageCount,
        List<String> categories,
        @JsonProperty("imageLinks") ImageLinks imageLinks,
        String language
) {
}
