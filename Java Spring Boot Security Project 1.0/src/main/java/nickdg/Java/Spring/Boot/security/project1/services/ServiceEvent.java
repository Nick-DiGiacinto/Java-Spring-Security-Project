package nickdg.Java.Spring.Boot.security.project1.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import nickdg.Java.Spring.Boot.security.project1.entities.Event;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionNotFound;
import nickdg.Java.Spring.Boot.security.project1.payloads.EventDTO;
import nickdg.Java.Spring.Boot.security.project1.repositories.EventDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ServiceEvent {
    @Autowired
    private EventDAO ed;
    @Autowired ServiceUser us;

    public Page<Event> findAll(int page, int size, String sort) {
        if (size > 40) size = 40;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return ed.findAll(p);
    }

    public Event findById(long id) {
        return ed.findById(id).orElseThrow(() -> new ExceptionNotFound(id));
    }

    public Event save(EventDTO payload) {
        Event newEvent = new Event(payload.title(), payload.description(), payload.place(), LocalDate.parse(payload.date()), payload.maxCapacity(), us.findById(payload.organizerId()));
        return ed.save(newEvent);
    }

    public Event update(long id, EventDTO payload) {
        Event found = this.findById(id);
        found.setTitle(payload.title());
        found.setDescription(payload.description());
        found.setPlace(payload.place());
        found.setDate(LocalDate.parse(payload.date()));
        found.setMaxCapacity(payload.maxCapacity());
        return ed.save(found);
    }
    public void delete(long id) {
        Event found = this.findById(id);
        ed.delete(found);
    }
}
