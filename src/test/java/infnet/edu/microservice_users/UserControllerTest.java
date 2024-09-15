<<<<<<< HEAD
package infnet.edu.microservice_users;

import infnet.edu.microservice_users.controller.UserController;
import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }
}
=======
//package infnet.edu.microservice_users;
//
//import infnet.edu.microservice_users.controller.UserController;
//import infnet.edu.microservice_users.model.User;
//import infnet.edu.microservice_users.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @Test
//    void testGetAllUsers() throws Exception {
//        when(userService.getAllUsers()).thenReturn(Arrays.asList(
//                new User("John Doe", "john.doe@example.com"),
//                new User("Jane Doe", "jane.doe@example.com")
//        ));
//
//        mockMvc.perform(get("/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].name").value("John Doe"))
//                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
//
//        verify(userService, times(1)).getAllUsers();
//    }
//
//    @Test
//    void testGetUserById_Success() throws Exception {
//        Long userId = 1L;
//        User mockUser = new User("John Doe", "john.doe@example.com");
//
//        when(userService.getUserById(userId)).thenReturn(mockUser);
//
//        mockMvc.perform(get("/users/{id}", userId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John Doe"));
//
//        verify(userService, times(1)).getUserById(userId);
//    }
//
//    @Test
//    void testAddUser() throws Exception {
//        User newUser = new User("New User", "new.user@example.com");
//
//        when(userService.addUser(any(User.class))).thenReturn(newUser);
//
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"New User\", \"email\": \"new.user@example.com\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("New User"));
//
//        verify(userService, times(1)).addUser(any(User.class));
//    }
//
//    @Test
//    void testDeleteUser() throws Exception {
//        Long userId = 1L;
//
//        doNothing().when(userService).deleteUser(userId);
//
//        mockMvc.perform(delete("/users/{id}", userId))
//                .andExpect(status().isOk());
//
//        verify(userService, times(1)).deleteUser(userId);
//    }
//}
>>>>>>> e552a3a3e6a1fda155ef31744f5ca2b8d9be1bc0
