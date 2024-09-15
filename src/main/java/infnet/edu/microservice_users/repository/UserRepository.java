package infnet.edu.microservice_users.repository;

import infnet.edu.microservice_users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    User findByUsername(String username);
}
