package blackapple.webbook.userPart.repositories;

import blackapple.webbook.userPart.models.ERole;
import blackapple.webbook.userPart.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
