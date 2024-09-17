package infnet.edu.microservice_users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

<<<<<<< HEAD
=======

>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/validate")
    public User validateUser(@RequestParam String username, @RequestParam String password) {
        return userService.validateUser(username, password);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
<<<<<<< HEAD
=======

>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
<<<<<<< HEAD
=======

>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006
        userService.deleteUser(id);
    }
}
