package nickdg.Java.Spring.Boot.security.project1.controllers;

import org.springframework.web.bind.annotation.*;
import nickdg.Java.Spring.Boot.security.project1.entities.Ticket;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.payloads.TicketDTO;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/tickets")
public class ControllerTicket {
    @Autowired
    private ServiceTicket ts;

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable long id) {
        return ts.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket saveNewTicket(@RequestBody @Validated TicketDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return ts.save(payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable long id) {
        ts.delete(id);
    }

}
