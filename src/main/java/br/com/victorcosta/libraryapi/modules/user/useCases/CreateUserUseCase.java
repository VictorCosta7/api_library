package br.com.victorcosta.libraryapi.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.UserFoundException;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserEntity execute(UserEntity userEntity) {
        this.userRepository.findByEmail(userEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });;

        var passwordEncode = this.passwordEncoder.encode(userEntity.getPassword());

        userEntity.setPassword(passwordEncode);

        return this.userRepository.save(userEntity);
    }
}
