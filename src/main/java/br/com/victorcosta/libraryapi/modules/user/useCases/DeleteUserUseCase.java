package br.com.victorcosta.libraryapi.modules.user.useCases;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        user.setDeletedAt(LocalDateTime.from(Instant.now()));

        this.userRepository.save(user);
    }
}
