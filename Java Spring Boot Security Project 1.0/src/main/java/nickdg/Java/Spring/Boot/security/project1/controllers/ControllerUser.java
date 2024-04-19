package nickdg.Java.Spring.Boot.security.project1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import nickdg.Java.Spring.Boot.security.project1.entities.User;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.payloads.UserDTO;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ControllerUser {
    @Autowired
    private ServiceUser us;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "12") int size,
                                  @RequestParam(defaultValue = "id") String sort) {
        return us.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserById(@PathVariable long id) {
        return us.findById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable long id, @RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return us.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable long id) {
        us.delete(id);
    }

    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User updateMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return us.update(currentAuthenticatedUser.getId(), payload);
    }

    @DeleteMapping("/me")
    public void deleteMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        us.delete(currentAuthenticatedUser.getId());
    }
}
