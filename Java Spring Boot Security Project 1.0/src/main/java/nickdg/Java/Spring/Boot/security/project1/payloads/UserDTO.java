package nickdg.Java.Spring.Boot.security.project1.payloads;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserDTO(@NotEmpty(message = "Username field must not be left empty.")
                      @Size(min = 2, max = 30, message = "Username field length must be between 2 and 30 characters.")
                      String username,
                      @NotEmpty(message = "Password field must not be left empty.")
                      @Size(min = 12, message = "Password must be at least 12 characters long.")
                      String password,
                      @NotEmpty(message = "Full name field must not be left empty.")
                      @Size(min = 2, max = 30, message = "Full name field length must be between 2 and 30 characters.")
                      String fullName,
                      @NotEmpty(message = "Email field must not be left empty.")
                      @Email(message = "Invalid email format.")
                      String email,
                      @NotEmpty(message = "Role field must not be left empty.")
                      @Pattern(regexp = "USER|ORGANIZER_OF_EVENTS", message = "Invalid role value (available: USER, ORGANIZER_OF_EVENTS")
                      String role) {
}
