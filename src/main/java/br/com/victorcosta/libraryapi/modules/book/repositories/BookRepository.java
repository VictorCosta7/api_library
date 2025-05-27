package br.com.victorcosta.libraryapi.modules.book.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcosta.libraryapi.modules.book.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitleAndAuthorId(String title, UUID authorId);
}
