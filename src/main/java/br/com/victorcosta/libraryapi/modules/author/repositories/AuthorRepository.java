package br.com.victorcosta.libraryapi.modules.author.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    Optional<AuthorEntity> findByFullNameAndDateOfBirth(String fullName, LocalDate DateOfBirth);
}
