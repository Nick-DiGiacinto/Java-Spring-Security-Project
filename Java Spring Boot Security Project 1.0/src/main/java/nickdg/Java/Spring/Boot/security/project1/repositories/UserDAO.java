package nickdg.Java.Spring.Boot.security.project1.repositories;

import nickdg.Java.Spring.Boot.security.project1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
