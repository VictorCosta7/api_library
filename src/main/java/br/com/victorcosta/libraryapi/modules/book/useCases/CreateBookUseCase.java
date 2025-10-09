package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.exeptions.BookFoundException;
import br.com.victorcosta.libraryapi.exeptions.InvalidIsbnException;
import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import br.com.victorcosta.libraryapi.providers.RestTemplateProvider;
import br.com.victorcosta.libraryapi.providers.dto.IndustryIdentifier;
import br.com.victorcosta.libraryapi.providers.dto.VolumeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class CreateBookUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CreateBookUseCase.class);

    private final BookRepository bookRepository;
    private final RestTemplateProvider restTemplateProvider;
    private final UserRepository userRepository;

    public CreateBookUseCase(BookRepository bookRepository, RestTemplateProvider restTemplateProvider, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.restTemplateProvider = restTemplateProvider;
        this.userRepository = userRepository;
    }

    public BookEntity execute(String isbn, Integer quantity, UUID userId) {
        Optional<VolumeInfo> apiResponseOptional = restTemplateProvider.getBookByISBN(isbn);

        if (apiResponseOptional.isEmpty()) {
            logger.error("API response is empty for ISBN: {}", isbn);
            throw new InvalidIsbnException();
        }

        var apiResponse = apiResponseOptional.get();

        var bookRegistered = bookRepository.findByIsbn(isbn);

        if (bookRegistered.isPresent()) {
            throw new BookFoundException();
        };

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        String bookIsbn = extractIsbnFromVolumeInfo(apiResponse.industryIdentifiers());

        Integer year = null;

        if (apiResponse.publishedDate() != null && apiResponse.publishedDate().length() >= 4) {
            year = Integer.parseInt(apiResponse.publishedDate().substring(0, 4));
        }

        BookEntity book = new BookEntity(
                bookIsbn,
                apiResponse.title(),
                apiResponse.authors(),
                apiResponse.publisher(),
                apiResponse.description(),
                year,
                apiResponse.pageCount(),
                apiResponse.categories(),
                apiResponse.imageLinks().thumbnail(),
                user.getId(),
                quantity
        );

        return bookRepository.save(book);
    }

    private String extractIsbnFromVolumeInfo(List<IndustryIdentifier> identifiers) {
        if (identifiers == null) {
            return null;
        }
        return identifiers.stream()
                .filter(id -> "ISBN_13".equals(id.type()))
                .map(IndustryIdentifier::identifier)
                .findFirst()
                .orElse(null);
    }
}