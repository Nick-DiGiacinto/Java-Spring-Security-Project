package nickdg.Java.Spring.Boot.security.project1.services;

import nickdg.Java.Spring.Boot.security.project1.payloads.TicketDTO;
import nickdg.Java.Spring.Boot.security.project1.repositories.TicketDAO;
import nickdg.Java.Spring.Boot.security.project1.entities.Event;
import nickdg.Java.Spring.Boot.security.project1.entities.Ticket;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTicket {
    @Autowired
    private TicketDAO td;
    @Autowired
    private ServiceUser us;
    @Autowired
    private ServiceEvent es;

    public Ticket findById(long id) {
        return td.findById(id).orElseThrow(() -> new ExceptionNotFound(id));
    }

    public void delete(long id) {
        Ticket found = this.findById(id);
        td.delete(found);
    }
    public Ticket save(TicketDTO payload) {
        Event event = es.findById(payload.eventId());
        if (event.getTickets().size() < event.getMaxCapacity()) {
            Ticket newTicket = new Ticket(us.findById(payload.userId()), event);
            return td.save(newTicket);
        } else throw new ExceptionBadRequest("Event is completely full.");
    }
}
