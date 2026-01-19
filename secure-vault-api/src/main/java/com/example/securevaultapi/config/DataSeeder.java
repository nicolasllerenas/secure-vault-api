package com.example.securevaultapi.config;

import com.example.securevaultapi.model.Account;
import com.example.securevaultapi.model.User;
import com.example.securevaultapi.repository.AccountRepository;
import com.example.securevaultapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, AccountRepository accountRepo) {
        return args -> {
            // Crear Usuario 1: Nicolas
            User nico = new User();
            nico.setUsername("nicolas");
            nico.setPassword("pass123");
            nico.setNationalId("12345678"); // ¡Se encriptará automáticamente!
            nico.setEmail("nico@utec.edu.pe"); // ¡Se encriptará automáticamente!
            userRepo.save(nico);

            Account accountNico = new Account();
            accountNico.setAccountNumber("CUENTA-001");
            accountNico.setBalance(new BigDecimal("1000.00"));
            accountNico.setUser(nico);
            accountRepo.save(accountNico);

            // Crear Usuario 2: Hacker
            User hacker = new User();
            hacker.setUsername("hacker");
            hacker.setPassword("pass000");
            hacker.setNationalId("87654321");
            hacker.setEmail("hacker@darkweb.com");
            userRepo.save(hacker);

            Account accountHacker = new Account();
            accountHacker.setAccountNumber("CUENTA-666");
            accountHacker.setBalance(new BigDecimal("0.00"));
            accountHacker.setUser(hacker);
            accountRepo.save(accountHacker);

            System.out.println("--- DATOS DE PRUEBA CARGADOS ---");
        };
    }
}