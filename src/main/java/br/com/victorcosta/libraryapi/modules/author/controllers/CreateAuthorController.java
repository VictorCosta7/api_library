package br.com.victorcosta.libraryapi.modules.author.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.author.useCases.CreateAuthorUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class CreateAuthorController {

    @Autowired
    private CreateAuthorUseCase createAuthorUseCase;
    
    @PostMapping 
    public ResponseEntity<Object> create(@Valid @RequestBody AuthorEntity authorEntity) {
        try {
            var result = this.createAuthorUseCase.execute(authorEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
