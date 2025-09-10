package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import br.com.victorcosta.libraryapi.providers.RestTemplateProvider;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;

import java.util.UUID;

@Service
public class CreateBookUseCase {
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

        var user = userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        var book = new BookEntity(
                user.getUserId(),
                user,
                apiResponse.isbn(),
                apiResponse.year(),
                apiResponse.synopsis(),
                apiResponse.authors(),
                apiResponse.title(),
                apiResponse.subtitle(),
                apiResponse.format(),
                apiResponse.pageCount(),
                apiResponse.location(),
                apiResponse.publisher(),
                apiResponse.provider(),
                apiResponse.coverUrl(),
                apiResponse.retailPrice(),
                apiResponse.subjects(),
                apiResponse.format()
        );

        return bookRepository.save(book);
    }
}
