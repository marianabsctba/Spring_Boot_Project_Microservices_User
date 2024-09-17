package infnet.edu.microservice_users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        logger.info("Fetching user with id: {}", id);
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        logger.info("Adding new user with username: {}", user.getUsername());
        return userService.addUser(user);
    }

    @GetMapping("/validate")
    public User validateUser(@RequestParam String username, @RequestParam String password) {
        logger.info("Validating user: {}", username);
        return userService.validateUser(username, password);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        logger.info("Updating user with id: {}", id);
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
    }
}
