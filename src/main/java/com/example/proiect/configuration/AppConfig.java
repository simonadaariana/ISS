package com.example.proiect.configuration;

import com.example.proiect.model.Bug;
import com.example.proiect.model.User;
import com.example.proiect.repository.BugRepository;
import com.example.proiect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Configuration
public class AppConfig {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AppConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, BugRepository bugRepo) {
        return args -> {
            User admin = new User("admin", "admin", passwordEncoder.encode("admin"), Role.ADMIN);
            User tester = new User("tester", "tester", passwordEncoder.encode("tester"), Role.TESTER);
            User programmer = new User("programmer", "programmer", passwordEncoder.encode("programmer"), Role.PROGRAMMER);

            Bug bug1 = new Bug("tester1","casa", "Case saltarete", false);
            Bug bug2 = new Bug("tester1","deal", "arca lui Noe", false);
            Bug bug3 = new Bug("tester2","vale", "Roboti rai", true);
            repository.saveAll(List.of(admin, tester, programmer));
            bugRepo.saveAll(List.of(bug1, bug2, bug3));
        };
    }
}

