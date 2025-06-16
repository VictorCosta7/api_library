package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserDto;
import br.com.victorcosta.libraryapi.modules.user.useCases.AuthUserUseCase;
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
    public String handler(@RequestBody AuthUserDto authUserDto) throws AuthenticationException {
       return this.authUserUseCase.execute(authUserDto);
    }
}
