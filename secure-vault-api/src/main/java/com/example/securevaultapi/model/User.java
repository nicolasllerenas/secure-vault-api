package com.example.securevaultapi.model;

import com.example.securevaultapi.security.encryption.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password; // Hash de BCrypt

    // --- SECURITY-BY-DESIGN: DATOS ENCRIPTADOS ---
    @Convert(converter = AttributeEncryptor.class)
    @Column(nullable = false)
    private String nationalId; // DNI

    @Convert(converter = AttributeEncryptor.class)
    private String email;
}