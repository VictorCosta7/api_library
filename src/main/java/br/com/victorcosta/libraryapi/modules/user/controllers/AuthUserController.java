package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserDto;
import br.com.victorcosta.libraryapi.modules.user.useCases.AuthUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController()
@RequestMapping("/users")
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    public AuthUserController(AuthUserUseCase authUserUseCase) {
        this.authUserUseCase = authUserUseCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> handler(@RequestBody AuthUserDto authUserDto) throws AuthenticationException {
       var result = this.authUserUseCase.execute(authUserDto);

       return  ResponseEntity.ok().body(result);
    }
}
