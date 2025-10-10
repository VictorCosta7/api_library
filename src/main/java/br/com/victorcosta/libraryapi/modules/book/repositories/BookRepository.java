package br.com.victorcosta.libraryapi.modules.book.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity>findByIsbn(String isbn);

    // TODO
    @Query(value = """
    SELECT *
    FROM books b
    WHERE
        (:title IS NULL OR :title = '' OR unaccent(b.title) ILIKE unaccent(CONCAT('%', :title, '%')))
        AND
        (:author IS NULL OR :author = '' OR unaccent(CAST(b.authors AS TEXT)) ILIKE unaccent(CONCAT('%', :author, '%')))
        AND
        (:category IS NULL OR :category = '' OR unaccent(CAST(b.subjects AS TEXT)) ILIKE unaccent(CONCAT('%', :category, '%')))
""", nativeQuery = true)
    Page<BookEntity> findByOptionalFilters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            Pageable pageable
    );
};
