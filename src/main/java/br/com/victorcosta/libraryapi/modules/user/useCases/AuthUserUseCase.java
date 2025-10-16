package br.com.victorcosta.libraryapi.modules.user.useCases;

import javax.naming.AuthenticationException;

import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserDto;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthUserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

     public AuthUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDTO execute(AuthUserDto authUserDto) throws AuthenticationException {
        UserEntity user = userRepository.findByEmail(authUserDto.email()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        boolean passwordMatches = this.passwordEncoder.matches(authUserDto.password(), user.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
       }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        List<String> roles = List.of(user.getRole().name());

        String token = JWT.create().withIssuer("libraryvc")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .withClaim("roles", roles)
                .sign(algorithm);

         return new AuthUserResponseDTO(token, expiresIn.toEpochMilli(), roles);
     }
}
