package br.com.victorcosta.libraryapi.providers;

import br.com.victorcosta.libraryapi.providers.dto.IsbdApiResponse;
import org.springframework.http.ResponseEntity; // Importe ResponseEntity
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateProvider() {
        this.restTemplate = new RestTemplate();
    }

    public IsbdApiResponse getBookByISBN(String isbn) {
        String apiUrl = "https://brasilapi.com.br/api/isbn/v1/";
        String fullUrl = apiUrl + isbn;

        try {
            ResponseEntity<IsbdApiResponse> responseEntity =
                    restTemplate.getForEntity(fullUrl, IsbdApiResponse.class);
            return  responseEntity.getBody();

        }   catch (Exception e) {
            System.err.println("Erro inesperado ao buscar ISBN " + isbn + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}