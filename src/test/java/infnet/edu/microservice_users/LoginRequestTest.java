package infnet.edu.microservice_users;

import infnet.edu.microservice_users.model.LoginRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

    @Test
    void testGetAndSetUsername() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john");
        assertEquals("john", loginRequest.getUsername());
    }

    @Test
    void testGetAndSetPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password123");
        assertEquals("password123", loginRequest.getPassword());
    }
}
