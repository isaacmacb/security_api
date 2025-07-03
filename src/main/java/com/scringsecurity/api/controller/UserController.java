package com.scringsecurity.api.controller;


import com.scringsecurity.api.entity.User;
import com.scringsecurity.api.repository.UserRepository;
import com.scringsecurity.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
