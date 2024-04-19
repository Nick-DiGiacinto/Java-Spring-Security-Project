package nickdg.Java.Spring.Boot.security.project1.controllers;

import nickdg.Java.Spring.Boot.security.project1.entities.Event;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.payloads.EventDTO;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/events")
public class ControllerEvent {
    @Autowired
    private ServiceEvent es;

    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int size,
                                     @RequestParam(defaultValue = "id") String sort) {
        return es.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable long id) {
        return es.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZER_OF_EVENT') or hasAuthority('ADMIN')")
    public void deleteEvent(@PathVariable long id) {
        es.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER_OF_EVENT') or hasAuthority('ADMIN')")
    public Event updateEvent(@PathVariable long id, @RequestBody @Validated EventDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return es.update(id, payload);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER_OF_EVENT') or hasAuthority('ADMIN')")
    public Event saveNewEvent(@RequestBody @Validated EventDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return es.save(payload);
    }
}
