package br.com.victorcosta.libraryapi.providers;

import br.com.victorcosta.libraryapi.providers.dto.GoogleBooksApiResponse;
import br.com.victorcosta.libraryapi.providers.dto.VolumeInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RestTemplateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateProvider() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<VolumeInfo> getBookByISBN(String isbn) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

        var response = restTemplate.getForObject(url, GoogleBooksApiResponse.class);

        if (response != null && response.totalItems() > 0) {
            return Optional.of(response.items().get(0).volumeInfo());
        }

        return Optional.empty();
    }
}