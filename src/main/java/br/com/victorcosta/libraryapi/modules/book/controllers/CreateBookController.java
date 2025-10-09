package br.com.victorcosta.libraryapi.modules.book.controllers;

import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.CreateBookRequestDTO;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> handler(@RequestBody CreateBookRequestDTO createBookRequestDTO, HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

        BookEntity book = createBookUseCase.execute(createBookRequestDTO.isbn(),createBookRequestDTO.quantity(),UUID.fromString(userId.toString()));

        return  ResponseEntity.ok().body(book);
    }
}
