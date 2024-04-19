package nickdg.Java.Spring.Boot.security.project1.controllers;


import nickdg.Java.Spring.Boot.security.project1.entities.User;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceAuth;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceUser;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionBadRequest;
import nickdg.Java.Spring.Boot.security.project1.payloads.LoginDTO;
import nickdg.Java.Spring.Boot.security.project1.payloads.ResponseLoginDTO;
import nickdg.Java.Spring.Boot.security.project1.payloads.UserDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {
    @Autowired
    private ServiceUser us;
    @Autowired
    private ServiceAuth as;

    @PostMapping("/login")
    public ResponseLoginDTO login(@RequestBody LoginDTO payload) {
        return as.login(payload);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveNewUser(@RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new ExceptionBadRequest(validation.getAllErrors());
        else return us.save(payload);
    }
}

