package br.com.victorcosta.libraryapi.modules.book.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.dto.CreateBookRequestDto;
import br.com.victorcosta.libraryapi.modules.book.useCases.CreateBookUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class CreateBookController {
    @Autowired
    private CreateBookUseCase createBookUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateBookRequestDto createBookRequestDto, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        var entity = new BookEntity();
        
        entity.setTitle(createBookRequestDto.getTitle());
        entity.setIsbn(createBookRequestDto.getIsbn());
        entity.setPublicationYear(createBookRequestDto.getPublicationYear());
        entity.setUserId(UUID.fromString(userId.toString()));

        try {
            var result = this.createBookUseCase.execute(entity);

            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
