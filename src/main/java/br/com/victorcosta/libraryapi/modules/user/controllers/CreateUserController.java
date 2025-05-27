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
        var userEntity = UserEntity.builder()
        .fullNAme(createUserDto.getFullName())
        .username(createUserDto.getUsername())
        .password(createUserDto.getPassword())
        .email(createUserDto.getEmail())
        .build();

        try {
           var result = this.createUserUseCase.execute(userEntity);

           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
