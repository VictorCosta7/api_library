package br.com.victorcosta.libraryapi.modules.book.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.CreateBookRequestDto;
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
    public ResponseEntity<Object> create(@Valid @RequestBody CreateBookRequestDto createBookRequestDto, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        var entity = new BookEntity();

        entity.setTitle(createBookRequestDto.title());
        entity.setIsbn(createBookRequestDto.isbn());
        entity.setPublicationYear(createBookRequestDto.publicationYear());
        entity.setBookCategory(createBookRequestDto.bookCategory());
        entity.setNumberOfPages(createBookRequestDto.numberOfPages());
        entity.setEdition(createBookRequestDto.edition());
        entity.setAuthorId(createBookRequestDto.authorId());
        entity.setUserId(UUID.fromString(userId.toString()));

        try {
            var result = this.createBookUseCase.execute(entity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
