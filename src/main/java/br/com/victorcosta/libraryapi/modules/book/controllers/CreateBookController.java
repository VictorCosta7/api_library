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
        var authorID = request.getAttribute("author_id");

        var jobEntity = BookEntity.builder()
        .title(createBookRequestDto.getTitle())
        .isbn(createBookRequestDto.getIsbn())
        .publicationYear(createBookRequestDto.getPublicationYear())
        .authorId(UUID.fromString(authorID.toString()))
        .build();
     
        try {
            var result = this.createBookUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
