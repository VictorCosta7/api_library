package br.com.victorcosta.libraryapi.modules.book.controllers;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.BookFilterDto;
import br.com.victorcosta.libraryapi.modules.book.useCases.ListBookUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ListBooksController {
    private final ListBookUseCase listBookUseCase;

    public ListBooksController(ListBookUseCase listBookUseCase) {
        this.listBookUseCase = listBookUseCase;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<BookEntity>> findAllFilteredAndPaged(
            BookFilterDto filters, Pageable pageable
    ) {
        Page<BookEntity> booksPage = listBookUseCase.execute(filters, pageable);

        return ResponseEntity.ok(booksPage);
    }
}
