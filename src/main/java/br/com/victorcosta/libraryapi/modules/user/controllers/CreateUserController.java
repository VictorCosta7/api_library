package br.com.victorcosta.libraryapi.modules.user.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.dto.CreateUserDto;
import br.com.victorcosta.libraryapi.modules.user.useCases.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class CreateUserController {
    private final CreateUserUseCase createUserUseCase;

    public CreateUserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserDto createUserDto) { // Use ResponseEntity<Void>
        var entity = new UserEntity();

        entity.setFullName(createUserDto.fullName());
        entity.setUsername(createUserDto.username());
        entity.setPassword(createUserDto.password());
        entity.setEmail(createUserDto.email());

        this.createUserUseCase.execute(entity);

        return ResponseEntity.noContent().build();
    }
}