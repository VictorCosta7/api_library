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
@RequestMapping("/user")
public class CreateUserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;
    
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateUserDto createUserDto) {
        var entity = new UserEntity();

        entity.setFullName(createUserDto.getFullName());
        entity.setUsername(createUserDto.getUsername());
        entity.setPassword(createUserDto.getPassword());
        entity.setEmail(createUserDto.getEmail());

        try {
           var result = this.createUserUseCase.execute(entity);

           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
