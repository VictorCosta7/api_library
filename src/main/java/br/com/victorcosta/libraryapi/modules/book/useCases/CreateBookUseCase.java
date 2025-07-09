package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import br.com.victorcosta.libraryapi.providers.RestTemplateProvider;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
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

        System.out.println("API RESPONSE ----------------------------------------");

        System.out.println(apiResponse);

        var user = userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        System.out.println(user);
        var book = new BookEntity();

        book.setUserId(user.getId());
        book.setUser(user);
        book.setIsbn(apiResponse.isbn());
        book.setYear(apiResponse.year());
        book.setSynopsis(apiResponse.synopsis());
        book.setAuthors(apiResponse.authors());
        book.setTitle(apiResponse.title());
        book.setSubtitle(apiResponse.subtitle());
        book.setFormat(apiResponse.format());
        book.setPageCount(apiResponse.pageCount());
        book.setLocation(apiResponse.location());
        book.setPublisher(apiResponse.publisher());
        book.setProvider(apiResponse.provider());
        book.setCoverUrl(apiResponse.coverUrl());
        book.setRetailPrice(apiResponse.retailPrice());
        book.setSubjects(apiResponse.subjects());
        book.setFormat(apiResponse.format());

        return bookRepository.save(book);
    }
}
