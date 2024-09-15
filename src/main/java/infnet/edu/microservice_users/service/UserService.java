package infnet.edu.microservice_users.service;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Listar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Criar novo usuário
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Atualizar usuário existente
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException(("Usuário não encontrado!")));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setUsername(updatedUser.getUsername());

        return userRepository.save(user);

    }

    // Deletar usuário
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
