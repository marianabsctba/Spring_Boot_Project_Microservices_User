package infnet.edu.microservice_users.service;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import infnet.edu.microservice_users.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Logger logger= LogManager.getLogger(UserService.class);

    public List<User> getAllUsers() {
        logger.info("getAllUsers");
        List<User> users = userRepository.findAll();
        logger.info("UserService:getAllUsers: {}", Mapper.mapToJsonString(users));
        return users;
    }


    public User getUserById(Long id) {
        logger.info("getUserById");
        User user =userRepository.findById(id).orElse(null);
        logger.info("UserService:getUserById: {}", Mapper.mapToJsonString(user));
        return user;
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }


    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException(("Usuário não encontrado!")));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setUsername(updatedUser.getUsername());

        return userRepository.save(user);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
            return user;
        }
            throw new RuntimeException("Invalid validation");
    }

}
