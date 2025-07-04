package br.com.victorcosta.libraryapi.modules.user.useCases;

import javax.naming.AuthenticationException;

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
        var user = userRepository.findByEmail(authUserDto.email()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var passwordMatches = this.passwordEncoder.matches(authUserDto.password(), user.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
       }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var roles = Arrays.asList("USER");

        var token = JWT.create().withIssuer("libraryvc")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .withClaim("roles", roles)
                .sign(algorithm);

         return new AuthUserResponseDTO(token, expiresIn.toEpochMilli(), roles);
     }
}
