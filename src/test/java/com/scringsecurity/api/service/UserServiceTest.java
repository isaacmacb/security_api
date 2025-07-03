package com.scringsecurity.api.service;


import com.scringsecurity.api.entity.User;
import com.scringsecurity.api.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {
    private final UserRepository repo = mock(UserRepository.class);
    private final UserService service = new UserService(repo);

    @Test
    void testFindByEmail() {
        User user = new User(1L, "Isaac", "isaacmacielbe@gmail.com", "123", "USER");

        when(repo.findByEmail("isaacmacielbe@gmail.com")).thenReturn(Optional.of(user));

        Optional<User> result = service.findByEmail("isaacmacielbe@gmail.com");

        assertTrue(result.isPresent());
        assertEquals("Isaac", result.get().getName());
    }

    @Test
    void testSave() {
        User user = new User(null, "Isaac", "isaac@email.com", "123", "USER");
        User saved = new User(1L, "Isaac", "isaac@email.com", "123", "USER");

        when(repo.save(user)).thenReturn(saved);

        User result = service.save(user);

        assertNotNull(result.getId());
        assertEquals("Isaac", result.getName());
    }

    @Test
    void testFindAll() {
        when(repo.findAll()).thenReturn(List.of(new User(), new User()));
        assertEquals(2, service.findAll().size());
    }

}