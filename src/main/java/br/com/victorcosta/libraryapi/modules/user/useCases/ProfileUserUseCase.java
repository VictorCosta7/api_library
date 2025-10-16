package br.com.victorcosta.libraryapi.modules.user.useCases;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileUserUseCase {
    private final UserRepository userRepository;

    public ProfileUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity execute(UUID id) {
      return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }
}
