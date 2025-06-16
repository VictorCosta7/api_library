package br.com.victorcosta.libraryapi.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void create(@RequestBody CreateUserDto createUserDto) {
        var entity = new UserEntity();

        entity.setFullName(createUserDto.getFullName());
        entity.setUsername(createUserDto.getUsername());
        entity.setPassword(createUserDto.getPassword());
        entity.setEmail(createUserDto.getEmail());

        var result = this.createUserUseCase.execute(entity);
    }
}
