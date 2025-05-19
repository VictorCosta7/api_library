package br.com.victorcosta.libraryapi.modules.author.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.victorcosta.libraryapi.exeptions.AutorNotFoundException;
import br.com.victorcosta.libraryapi.modules.author.dto.AtuhAuthorDto;
import br.com.victorcosta.libraryapi.modules.author.repositories.AuthorRepository;

@Service
public class AuthAuthorUseCase {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public String execute(AtuhAuthorDto atuhAuthorDto) throws AuthenticationException {
        var author = authorRepository.findByEmail(atuhAuthorDto.getEmail()).orElseThrow(() -> {
            throw new AutorNotFoundException();
        });

       var passwordMatches = this.passwordEncoder.matches(atuhAuthorDto.getPassword(), author.getPassword());

       if(!passwordMatches) {
        throw new AuthenticationException();
       }

       Algorithm algorithm = Algorithm.HMAC256(secretKey);

       var token = JWT.create().withIssuer("libraryvc")
       .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
       .withSubject(author.getId().toString())
       .sign(algorithm);

       return token;
    }
}
