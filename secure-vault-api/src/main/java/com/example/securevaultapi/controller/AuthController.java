package com.example.securevaultapi.controller;

import com.example.securevaultapi.model.User;
import com.example.securevaultapi.repository.UserRepository;
import com.example.securevaultapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        // En un caso real, aquí validaríamos la contraseña con BCrypt
        String username = request.get("username");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}