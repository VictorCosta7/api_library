package br.com.victorcosta.libraryapi.modules.user.useCases;

import br.com.victorcosta.libraryapi.modules.user.dto.CreateUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.UserFoundException;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserEntity execute(CreateUserDto createUserDto) {
        this.userRepository.findByEmail(createUserDto.email()).ifPresent((user) -> {
            throw new UserFoundException();
        });;

        String passwordEncode = this.passwordEncoder.encode(createUserDto.password());

        UserEntity user = new UserEntity(
                createUserDto.fullName(),
                createUserDto.username(),
                createUserDto.email(),
                passwordEncode
        );

        return this.userRepository.save(user);
    }
}
