package br.com.victorcosta.libraryapi.config;

import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.domain.UserRole;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateAdminUserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.seed.enabled:false}")
    private boolean adminSeedEnabled;

    @Value("${app.admin.seed.username:admin}")
    private String adminUsername;

    @Value("${app.admin.seed.password:Admin@123}")
    private String adminPassword;

    @Value("${app.admin.seed.email:admin@library.com}")
    private String adminEmail;

    @Value("${app.admin.seed.fullname:System Administrator}")
    private String adminFullName;

    @Value("${app.admin.seed.phone:(99) 99999-9999}")
    private String adminPhone;


    @Autowired
    public CreateAdminUserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!adminSeedEnabled) {
            System.out.println("Admin user seeding is disabled.");
            return;
        }

        Optional<UserEntity> existingAdmin = userRepository.findByUsername(adminUsername);

        if (existingAdmin.isPresent()) {
            System.out.println("Admin user already exists. Seeding skipped.");
            return;
        }

        UserEntity admin = new UserEntity();
        admin.setFullName(adminFullName);
        admin.setUsername(adminUsername);
        admin.setEmail(adminEmail);
        admin.setPhone(adminPhone);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(UserRole.ADMIN);

        userRepository.save(admin);

        System.out.println("Admin user created successfully.");
        }
    }