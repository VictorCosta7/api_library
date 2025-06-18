package br.com.victorcosta.libraryapi.modules.author.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    Optional<AuthorEntity> findByFullName(String fullName);
    Page<AuthorEntity> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);
    Page<AuthorEntity> findAll(Pageable pageable);
}
