package nickdg.Java.Spring.Boot.security.project1.services;

import nickdg.Java.Spring.Boot.security.project1.entities.User;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionUnauthorized;
import nickdg.Java.Spring.Boot.security.project1.payloads.LoginDTO;
import nickdg.Java.Spring.Boot.security.project1.payloads.ResponseLoginDTO;
import nickdg.Java.Spring.Boot.security.project1.security.ToolsJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceAuth {
    @Autowired
    private ServiceUser us;
    @Autowired
    private ToolsJWT tools;
    @Autowired
    private PasswordEncoder bcrypt;

    public ResponseLoginDTO login(LoginDTO payload) {
        User found = us.findByUsername(payload.username());
        if (bcrypt.matches(payload.password(), found.getPassword())) return new ResponseLoginDTO(tools.createToken(found));
        else throw new ExceptionUnauthorized("The credentials are invalid, please try again.");
    }
}
