package br.com.victorcosta.libraryapi.modules.author.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.AuthorFoundException;
import br.com.victorcosta.libraryapi.exeptions.AutorNotFoundException;
import br.com.victorcosta.libraryapi.modules.author.dto.AtuhAuthorDto;
import br.com.victorcosta.libraryapi.modules.author.repositories.AuthorRepository;

@Service
public class AuthAuthorUseCase {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AtuhAuthorDto atuhAuthorDto) throws AuthenticationException {
        var author = authorRepository.findByEmail(atuhAuthorDto.getEmail()).orElseThrow(() -> {
            throw new AutorNotFoundException();
        });

       var passwordMatches = this.passwordEncoder.matches(atuhAuthorDto.getPassword(), author.getPassword());

       if(!passwordMatches) {
        throw new AuthenticationException();
       }
    }
}
