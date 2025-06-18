package br.com.victorcosta.libraryapi.modules.author.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.author.dto.CreateAuthorDto;
import br.com.victorcosta.libraryapi.modules.author.useCases.CreateAuthorUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class CreateAuthorController {

    private final CreateAuthorUseCase createAuthorUseCase;

    public CreateAuthorController(CreateAuthorUseCase createAuthorUseCase) {
        this.createAuthorUseCase = createAuthorUseCase;
    }

    @PostMapping 
    public ResponseEntity<Object> create(@Valid @RequestBody CreateAuthorDto createAuthorDto, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");
        
        var entity = new AuthorEntity();
        entity.setFullName(createAuthorDto.fullName());
        entity.setNationality(createAuthorDto.nationality());
        entity.setUserId(UUID.fromString(userId.toString()));

        try {
            var result = this.createAuthorUseCase.execute(entity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
