package infnet.edu.microservice_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import infnet.edu.microservice_users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
