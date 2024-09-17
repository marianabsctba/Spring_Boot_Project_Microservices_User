package infnet.edu.microservice_users;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import infnet.edu.microservice_users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock a user
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test User");
        mockUser.setEmail("test@example.com");
        mockUser.setUsername("testuser");
        mockUser.setPassword("password");
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("Test User", users.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals("Test User", user.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User user = userService.getUserById(1L);

        assertNull(user);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUser() {
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User savedUser = userService.addUser(mockUser);

        assertNotNull(savedUser);
        assertEquals("Test User", savedUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User updatedUser = new User();
        updatedUser.setName("Updated User");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("newpassword");
        updatedUser.setUsername("updateduser");

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Updated User", result.getName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User updatedUser = new User();
        updatedUser.setName("Updated User");
        updatedUser.setEmail("updated@example.com");

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, updatedUser));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).existsById(1L);
    }

    @Test
    void testValidateUser_Success() {
        when(userRepository.findByUsername("testuser")).thenReturn(mockUser);

        User validatedUser = userService.validateUser("testuser", "password");

        assertNotNull(validatedUser);
        assertEquals("Test User", validatedUser.getName());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testValidateUser_InvalidCredentials() {
        when(userRepository.findByUsername("testuser")).thenReturn(mockUser);

        assertThrows(RuntimeException.class, () -> userService.validateUser("testuser", "wrongpassword"));
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testValidateUser_NotFound() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.validateUser("nonexistentuser", "password"));
        verify(userRepository, times(1)).findByUsername("nonexistentuser");
    }
}
