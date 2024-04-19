package nickdg.Java.Spring.Boot.security.project1.entities;

import nickdg.Java.Spring.Boot.security.project1.enums.TypeOfUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;
import lombok.AccessLevel;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private TypeOfUser role;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    public User(String username, String password, String fullName, String email, TypeOfUser role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
