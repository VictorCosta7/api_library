package br.com.victorcosta.libraryapi.modules.author.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcosta.libraryapi.modules.author.dto.AtuhAuthorDto;
import br.com.victorcosta.libraryapi.modules.author.useCases.AuthAuthorUseCase;

@RestController
@RequestMapping("/auth")
public class AuthAuthorController {

    @Autowired
    private AuthAuthorUseCase authAuthorUseCase;
    
    @PostMapping("/author")
    public ResponseEntity<Object> authenticate(@RequestBody AtuhAuthorDto atuhAuthorDto) {
        try {
            var result = this.authAuthorUseCase.execute(atuhAuthorDto);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
