package br.com.victorcosta.libraryapi.modules.book.useCases;

import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.BookFoundException;
import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;

@Service
public class CreateBookUseCase {
    private final BookRepository bookRepository;

    public CreateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity execute(BookEntity bookEntity) {
       this.bookRepository.findByTitleAndAuthorId(bookEntity.getTitle(), bookEntity.getAuthorId()).ifPresent((book) -> {
           throw new BookFoundException();
       });

        return this.bookRepository.save(bookEntity);
    }
}
