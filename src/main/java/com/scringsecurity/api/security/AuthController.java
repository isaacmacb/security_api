package com.scringsecurity.api.security;
import com.scringsecurity.api.entity.User;
import com.scringsecurity.api.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository repo;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository repo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.repo = repo;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.get("email"), body.get("password"))
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());

        return Map.of("token", token);
    }

    // Criação de usuários apenas para teste
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }
}
