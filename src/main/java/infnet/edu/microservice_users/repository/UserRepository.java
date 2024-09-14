package infnet.edu.microservice_users.repository;

import infnet.edu.microservice_users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // **NOVO** - Buscar usuário pelo nome
    User findByName(String name);
}
