package br.com.victorcosta.libraryapi.modules.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.useCases.CreateBookUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class CreateBookController {
    
    @Autowired
    private CreateBookUseCase createBookUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody BookEntity bookEntity) {
        try {
            var result = this.createBookUseCase.execute(bookEntity);

            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
