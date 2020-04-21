package blackapple.webbook.repositories;

import blackapple.webbook.models.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
