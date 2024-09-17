package infnet.edu.microservice_users.service;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import infnet.edu.microservice_users.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    Logger logger= LogManager.getLogger(UserService.class);

    public List<User> getAllUsers() {
<<<<<<< HEAD
        logger.info("Fetching all users");
        return userRepository.findAll();
=======
        logger.info("getAllUsers");
        List<User> users = userRepository.findAll();
        logger.info("UserService:getAllUsers: {}", Mapper.mapToJsonString(users));
        return users;
>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006
    }

    public User getUserById(Long id) {
<<<<<<< HEAD
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.error("User with id {} not found", id);
        } else {
            logger.info("User with id {} found", id);
        }
=======
        logger.info("getUserById");
        User user =userRepository.findById(id).orElse(null);
        logger.info("UserService:getUserById: {}", Mapper.mapToJsonString(user));
>>>>>>> f5a83cbadfba81918ffa623228b9eb749b5ff006
        return user;
    }

    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        logger.info("User with id {} added successfully", savedUser.getId());
        return savedUser;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User with id {} not found for update", id);
            return new RuntimeException("User not found!");
        });

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setUsername(updatedUser.getUsername());

        User savedUser = userRepository.save(user);
        logger.info("User with id {} updated successfully", id);
        return savedUser;
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            logger.info("User with id {} deleted successfully", id);
        } else {
            logger.error("User with id {} not found for deletion", id);
            throw new RuntimeException("User not found!");
        }
    }

    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("User with username {} not found", username);
            throw new RuntimeException("User not found");
        }

        if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
            logger.info("User {} validated successfully", username);
            return user;
        } else {
            logger.error("Invalid credentials for user {}", username);
            throw new RuntimeException("Invalid validation");
        }
    }
}
