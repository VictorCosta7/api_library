package br.com.victorcosta.libraryapi.providers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
record Dimensions(
        Double width,
        Double height,
        String unit
) {}

public record IsbdApiResponse(
        String isbn,
        String title,
        String subtitle,
        List<String> authors,
        String publisher,
        String synopsis,
        Dimensions dimensions,
        Integer year,
        String format,
        @JsonProperty("page_count") Integer pageCount,
        List<String> subjects,
        String location,
        @JsonProperty("retail_price") Double retailPrice,
        @JsonProperty("cover_url") String coverUrl,
        String provider
) {
}
