package blackapple.webbook.userPart.repositories;

import blackapple.webbook.userPart.model.ERole;
import blackapple.webbook.userPart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
