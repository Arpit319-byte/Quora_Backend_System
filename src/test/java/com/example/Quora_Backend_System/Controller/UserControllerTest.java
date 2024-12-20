package com.example.Quora_Backend_System.Controller;

import com.example.Quora_Backend_System.DTO.UserDTO;
import com.example.Quora_Backend_System.controller.UserController;
import com.example.Quora_Backend_System.model.User;
import com.example.Quora_Backend_System.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user = new User();
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.createUser(new UserDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, new UserDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testDeleteUser() {
        when(userService.deleteUser(1L)).thenReturn(true);

        ResponseEntity<?> response = userController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(1L);
    }
}
