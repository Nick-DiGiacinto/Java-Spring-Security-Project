package nickdg.Java.Spring.Boot.security.project1.repositories;

import nickdg.Java.Spring.Boot.security.project1.entities.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {
}
