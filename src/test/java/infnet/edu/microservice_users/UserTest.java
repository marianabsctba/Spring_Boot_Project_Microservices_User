package infnet.edu.microservice_users;


import infnet.edu.microservice_users.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testGetAndSetName() {
        User user = new User();
        user.setName("John");
        assertEquals("John", user.getName());
    }

    @Test
    void testGetAndSetEmail() {
        User user = new User();
        user.setEmail("john@example.com");
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testGetAndSetUsername() {
        User user = new User();
        user.setUsername("john123");
        assertEquals("john123", user.getUsername());
    }

    @Test
    void testGetAndSetPassword() {
        User user = new User();
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }
}
