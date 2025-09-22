package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.exeptions.BookFoundException;
import br.com.victorcosta.libraryapi.exeptions.InvalidIsbnException;
import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import br.com.victorcosta.libraryapi.providers.RestTemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;

import java.util.UUID;

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

    public BookEntity execute(String isbn, UUID userId) {
        var apiResponse = restTemplateProvider.getBookByISBN(isbn);

        if (apiResponse == null) {
            logger.error("API response is null for ISBN: {}", isbn);
            throw new InvalidIsbnException();
        }

        var bookRegistered = bookRepository.findByIsbn(isbn);

        if (bookRegistered.isPresent()) {
            throw new BookFoundException();
        };

        var user = userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        var book = new BookEntity(
                apiResponse.isbn(),
                apiResponse.title(),
                apiResponse.subtitle(),
                apiResponse.authors(),
                apiResponse.publisher(),
                apiResponse.synopsis(),
                apiResponse.year(),
                apiResponse.format(),
                apiResponse.pageCount(),
                apiResponse.subjects(),
                apiResponse.location(),
                apiResponse.retailPrice(),
                apiResponse.coverUrl(),
                apiResponse.provider(),
                user
        );

        return bookRepository.save(book);
    }
}
