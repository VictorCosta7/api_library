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
    private final AuthorRepository authorRepository;

    public CreateAuthorUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorEntity execute(AuthorEntity authorEntity) {
        this.authorRepository.findByFullNameAndDateOfBirth(authorEntity.getFullName(), authorEntity.getDateOfBirth()).ifPresent((author) -> {
            throw new AuthorFoundException();
        });

        return this.authorRepository.save(authorEntity);
    }
}
