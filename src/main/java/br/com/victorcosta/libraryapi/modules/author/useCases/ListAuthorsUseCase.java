package br.com.victorcosta.libraryapi.modules.author.useCases;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.author.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAuthorsUseCase {
    private final AuthorRepository authorRepository;

    public ListAuthorsUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<AuthorEntity> execute(String fullName, Pageable pageable) {
        if(fullName != null && !fullName.isEmpty()) {
            return this.authorRepository.findByFullNameContainingIgnoreCase(fullName, pageable);
        } else {
            return this.authorRepository.findAll(pageable);
        }
    }
}
