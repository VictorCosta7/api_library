package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.user.useCases.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class CreateUserController {
    private final CreateUserUseCase createUserUseCase;

    public CreateUserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> handler(@Valid @RequestBody CreateUserDto body) {
        this.createUserUseCase.execute(body);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}