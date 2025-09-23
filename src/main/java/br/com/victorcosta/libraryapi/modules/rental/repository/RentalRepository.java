package br.com.victorcosta.libraryapi.modules.rental.repository;

import br.com.victorcosta.libraryapi.modules.rental.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<RentalEntity, UUID> {
}
