package br.com.victorcosta.libraryapi.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.BookFoundException;
import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;

@Service
public class CreateBookUseCase {
    
    @Autowired
    private BookRepository bookRepository;

    public BookEntity execute(BookEntity bookEntity) {
        this.bookRepository.findByTitleAndAuthorId(bookEntity.getTitle(), bookEntity.getUserId()).ifPresent((book) -> {
            throw new BookFoundException();
        });

        return this.bookRepository.save(bookEntity);
    }
}
