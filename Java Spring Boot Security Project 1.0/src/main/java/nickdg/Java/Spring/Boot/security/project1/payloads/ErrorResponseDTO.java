package nickdg.Java.Spring.Boot.security.project1.payloads;
import java.time.LocalDateTime;

public record ErrorResponseDTO(String msg, LocalDateTime timestamp) {
}
