package infnet.edu.microservice_users;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName_shouldReturnUser() {
        User user = new User("John", "john@example.com", "password", "john123");
        userRepository.save(user);

        User foundUser = userRepository.findByName("John");
        assertEquals("John", foundUser.getName());
    }

    @Test
    void findByUsername_shouldReturnUser() {
        User user = new User("John", "john@example.com", "password", "john123");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("john123");
        assertEquals("john123", foundUser.getUsername());
    }
}

