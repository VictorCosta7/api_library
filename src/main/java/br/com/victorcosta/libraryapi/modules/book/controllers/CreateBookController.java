package br.com.victorcosta.libraryapi.modules.book.controllers;

import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.CreateBookRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.book.useCases.CreateBookUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class CreateBookController {
    private final CreateBookUseCase createBookUseCase;

    public CreateBookController(CreateBookUseCase createBookUseCase) {
        this.createBookUseCase = createBookUseCase;
    }

    @PostMapping
    @Tag(name = "Books", description = "Operations related to managing the book inventory.")
    @Operation(
            summary = "Add a new book to the inventory",
            description = "Registers a new book, along with its initial quantity, into the library inventory. The book will be associated with the user performing the registration (librarian/admin)."
    )
    public ResponseEntity<Object> handler(@RequestBody CreateBookRequestDTO createBookRequestDTO, HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

        BookEntity book = createBookUseCase.execute(createBookRequestDTO.isbn(),createBookRequestDTO.quantity(),UUID.fromString(userId.toString()));

        return  ResponseEntity.ok().body(book);
    }
}
