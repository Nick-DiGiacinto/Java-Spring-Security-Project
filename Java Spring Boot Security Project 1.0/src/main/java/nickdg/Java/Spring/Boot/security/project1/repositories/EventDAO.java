package nickdg.Java.Spring.Boot.security.project1.repositories;

import org.springframework.stereotype.Repository;
import nickdg.Java.Spring.Boot.security.project1.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
}