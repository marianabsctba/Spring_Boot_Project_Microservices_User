package infnet.edu.microservice_users.service;

import infnet.edu.microservice_users.model.User;
import infnet.edu.microservice_users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        // Verifica se o usuário existe no banco de dados
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Verificar se os valores de updatedUser não são nulos antes de definir
                    if (updatedUser.getName() != null) {
                        existingUser.setName(updatedUser.getName());
                    }
                    if (updatedUser.getEmail() != null) {
                        existingUser.setEmail(updatedUser.getEmail());
                    }
                    // Atualize outros campos conforme necessário
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    // Deletar usuário
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
