package nickdg.Java.Spring.Boot.security.project1.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import nickdg.Java.Spring.Boot.security.project1.entities.User;
import nickdg.Java.Spring.Boot.security.project1.enums.TypeOfUser;
import nickdg.Java.Spring.Boot.security.project1.payloads.UserDTO;
import nickdg.Java.Spring.Boot.security.project1.repositories.UserDAO;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ServiceUser {
    @Autowired
    private UserDAO ud;

    public Page<User> findAll(int page, int size, String sort) {
        if (size > 40) size = 40;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return ud.findAll(p);
    }

    public User findById(long id) {
        return ud.findById(id).orElseThrow(() -> new ExceptionNotFound(id));
    }

    public User update(long id, UserDTO payload) {
        User found = this.findById(id);
        if (!Objects.equals(found.getUsername(), payload.username()) && !ud.existsByUsername(payload.username())) found.setUsername(payload.username());
        else if (!Objects.equals(found.getUsername(), payload.username()) && ud.existsByUsername(payload.username())) throw new ExceptionBadRequest("Username " + payload.username() + " is not available.");
        found.setPassword(payload.password());
        found.setFullName(payload.fullName());
        if (!Objects.equals(found.getEmail(), payload.email()) && !ud.existsByEmail(payload.email())) found.setEmail(payload.email());
        else if (!Objects.equals(found.getEmail(), payload.email()) && ud.existsByEmail(payload.email())) throw new ExceptionBadRequest("Email " + payload.email() + " is already being used");
        found.setRole(TypeOfUser.valueOf(payload.role()));
        return ud.save(found);
    }

    public User findByUsername(String username) {
        return ud.findByUsername(username).orElseThrow(() -> new ExceptionNotFound("The username " + username + " was not found."));
    }

    public User save(UserDTO payload) {
        if (ud.existsByUsername(payload.username())) throw new ExceptionBadRequest("The username " + payload.username() + " is not available.");
        else if (ud.existsByEmail(payload.email())) throw new ExceptionBadRequest("Email " + payload.email() + " is already being used");
        else {
            User newUser = new User(payload.username(), payload.password(), payload.fullName(), payload.email(), TypeOfUser.valueOf(payload.role()));
            return ud.save(newUser);
        }
    }

    public void delete(long id) {
        User found = this.findById(id);
        ud.delete(found);
    }
}
