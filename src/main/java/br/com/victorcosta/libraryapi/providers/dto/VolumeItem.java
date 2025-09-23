package br.com.victorcosta.libraryapi.providers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VolumeItem(
        String id,
        @JsonProperty("volumeInfo") VolumeInfo volumeInfo,
        @JsonProperty("saleInfo") Object saleInfo
) {
}
