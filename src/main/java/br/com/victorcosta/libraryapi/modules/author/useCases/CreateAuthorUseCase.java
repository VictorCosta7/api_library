package br.com.victorcosta.libraryapi.modules.author.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.AuthorEmailException;
import br.com.victorcosta.libraryapi.exeptions.AuthorFoundException;
import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.author.repositories.AuthorRepository;

@Service
public class CreateAuthorUseCase {
    
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthorEntity execute(AuthorEntity authorEntity) {
        this.authorRepository.findByFullNameAndDateOfBirth(authorEntity.getFullName(), authorEntity.getDateOfBirth()).ifPresent((author) -> {
            throw new AuthorFoundException();
        });

        this.authorRepository.findByEmail(authorEntity.getEmail()).ifPresent((author) -> {
            throw new AuthorEmailException();
        });

        var passwordEncoded = passwordEncoder.encode(authorEntity.getPassword());
        authorEntity.setPassword(passwordEncoded);

       return this.authorRepository.save(authorEntity);
    }   
}
