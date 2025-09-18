package br.com.victorcosta.libraryapi.modules.book.repositories;

import java.util.Optional;
import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity>findByIsbn(String isbn);
}
