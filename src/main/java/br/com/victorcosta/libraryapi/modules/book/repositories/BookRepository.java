package br.com.victorcosta.libraryapi.modules.book.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitleAndAuthorId(String title, UUID authorId);

    @Query("SELECT b FROM books b JOIN b.author a WHERE " +
            "LOWER(b.title) LIKE :filter OR " +
            "LOWER(a.fullName) LIKE :filter OR " +
            "LOWER(b.bookCategory) LIKE :filter")
    Page<BookEntity> findByTitleAuthorNameOrGenre(@Param("filter") String filter, Pageable pageable);
}
