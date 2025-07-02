package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListBooksUseCase {
    private final BookRepository bookRepository;

    public ListBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookEntity> execute(String filter, Pageable pageable) {
        if (filter == null || filter.trim().isEmpty()) {
            return bookRepository.findAll(pageable);
        } else {
            return bookRepository.findByTitleAuthorNameOrGenre(filter.trim(), pageable);
        }
    }
}
