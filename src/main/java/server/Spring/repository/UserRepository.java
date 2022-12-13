package server.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
