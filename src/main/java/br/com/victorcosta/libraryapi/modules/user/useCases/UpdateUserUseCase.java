package br.com.victorcosta.libraryapi.modules.user.useCases;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.dto.UpdateUserDto;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId, UpdateUserDto updateUserDto) {

        UserEntity user = this.userRepository.findById(userId).orElseThrow(() -> {
            return new UserNotFoundException();
        });

        String newFullName = updateUserDto.fullName();
        if (newFullName != null && !newFullName.isBlank()) {
            user.setFullName(newFullName);
        }

        String newUsername = updateUserDto.username();
        if (newUsername != null && !newUsername.isBlank()) {
            user.setUsername(newUsername);
        }

        String newEmail = updateUserDto.email();
        if (newEmail != null && !newEmail.isBlank()) {
            user.setEmail(newEmail);
        }

        String newPhone = updateUserDto.phone();
        if (newPhone != null && !newPhone.isBlank()) {
            user.setPhone(newPhone);
        }

        this.userRepository.save(user);
    }
}