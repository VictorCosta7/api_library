package br.com.victorcosta.libraryapi.modules.user.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserEntity>findByEmail(String email);
}
