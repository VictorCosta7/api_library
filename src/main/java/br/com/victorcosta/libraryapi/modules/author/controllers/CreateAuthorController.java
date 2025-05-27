package br.com.victorcosta.libraryapi.modules.author.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.author.dto.CreateAuthorDto;
import br.com.victorcosta.libraryapi.modules.author.useCases.CreateAuthorUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class CreateAuthorController {

    @Autowired
    private CreateAuthorUseCase createAuthorUseCase;
    
    @PostMapping 
    public ResponseEntity<Object> create(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        var entity = AuthorEntity.builder()
        .fullName(createAuthorDto.getFullNAme())
        .email(createAuthorDto.getEmail())
        .password(createAuthorDto.getPassword())
        .nationality(createAuthorDto.getNationality())
        .dateOfBirth(createAuthorDto.getDateOfBirth())
        .build();

        try {
            var result = this.createAuthorUseCase.execute(entity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
