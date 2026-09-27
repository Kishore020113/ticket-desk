package ticket.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.com.example.demo.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
