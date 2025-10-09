package br.com.victorcosta.libraryapi.modules.book.useCases;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.BookFilterDto;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListBookUseCase {
    private final BookRepository bookRepository;

    public ListBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookEntity> execute(BookFilterDto filters, Pageable pageable) {
        return bookRepository.findByOptionalFilters(filters.author(), filters.category(), filters.title(), pageable);
    };
}
