package br.com.victorcosta.libraryapi.modules.book.controllers;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.BookFilterDto;
import br.com.victorcosta.libraryapi.modules.book.useCases.ListBookUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Tag(name = "Books", description = "Operations related to managing the book inventory.")
    @Operation(
            summary = "Retrieve a paginated list of books",
            description = "Retrieves books from the inventory, supporting optional filtering by title, author, and category, and providing results in a paginated format."
    )
    public ResponseEntity<Page<BookEntity>> findAllFilteredAndPaged(
            BookFilterDto filters, Pageable pageable
    ) {
        Pageable pagination = PageRequest.of(0, 10);
        Page<BookEntity> booksPage = listBookUseCase.execute(filters, pagination);

        return ResponseEntity.ok(booksPage);
    }
}
