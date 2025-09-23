package br.com.victorcosta.libraryapi.providers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
 public record GoogleBooksApiResponse(
        String kind,
        Integer totalItems,
        List<VolumeItem> items
) {
}

