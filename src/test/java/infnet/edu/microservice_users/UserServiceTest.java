package infnet.edu.microservice_users;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import infnet.edu.microservice_users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User("John", "john@example.com", "password", "john123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals("John", foundUser.getName());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void addUser_shouldSaveUser() {
        User user = new User("John", "john@example.com", "password", "john123");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.addUser(user);

        assertEquals("John", savedUser.getName());
        assertEquals("john@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser_shouldUpdateUser() {
        User existingUser = new User("John", "john@example.com", "password", "john123");
        User updatedUser = new User("John Updated", "updated@example.com", "password", "john123");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertEquals("John Updated", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUser_shouldCallRepositoryDelete() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void validateUser_shouldReturnValidUser() {
        User user = new User("John", "john@example.com", "password", "john123");
        when(userRepository.findByUsername("john123")).thenReturn(user);

        User validatedUser = userService.validateUser("john123", "password");

        assertEquals("john123", validatedUser.getUsername());
        assertEquals("password", validatedUser.getPassword());
    }

    @Test
    void validateUser_shouldThrowException_whenInvalidCredentials() {
        when(userRepository.findByUsername("john123")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.validateUser("john123", "password"));
    }
}