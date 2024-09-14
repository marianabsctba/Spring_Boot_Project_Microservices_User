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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

//    @Test
//    void testGetAllUsers() {
//        // Mock do repositório retornando uma lista de usuários
//        when(userRepository.findAll()).thenReturn(Arrays.asList(
//                new User("John Doe", "john.doe@example.com"),
//                new User("Jane Doe", "jane.doe@example.com")
//        ));
//
//        var users = userService.getAllUsers();
//        assertEquals(2, users.size());
//        verify(userRepository, times(1)).findAll();
//    }

    @Test
    void testGetUserById_Success() {
        Long userId = 1L;
        User mockUser = new User("John Doe", "john.doe@example.com");

        // Mock do repositório para encontrar um usuário
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserById(userId);
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        Long userId = 2L;

        // Mock para retornar vazio quando o usuário não for encontrado
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testAddUser() {
        User newUser = new User("New User", "new.user@example.com");

        // Mock para salvar o usuário
        when(userRepository.save(newUser)).thenReturn(newUser);

        User createdUser = userService.addUser(newUser);
        assertNotNull(createdUser);
        assertEquals("New User", createdUser.getName());
        assertEquals("new.user@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        // Mock para a exclusão de um usuário
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
