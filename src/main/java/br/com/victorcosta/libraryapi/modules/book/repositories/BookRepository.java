package br.com.victorcosta.libraryapi.modules.book.repositories;

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

    @Query(value = "SELECT * FROM books b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) OR " +
            "(:author IS NULL OR LOWER(CAST(b.authors AS VARCHAR)) LIKE LOWER(CONCAT('%\"', :author, '\"%'))) OR " +
            "(:category IS NULL OR LOWER(CAST(b.subjects AS VARCHAR)) LIKE LOWER(CONCAT('%\"', :category, '\"%')))",
            nativeQuery = true)
    Page<BookEntity> findByOptionalFilters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            Pageable pageable
    );
    };
