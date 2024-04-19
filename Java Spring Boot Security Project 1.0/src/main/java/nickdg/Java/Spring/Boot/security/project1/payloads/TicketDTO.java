package nickdg.Java.Spring.Boot.security.project1.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record TicketDTO(@Min(value = 1)
                        @NotNull(message = "User ID field must not be left null.")
                        long userId,
                        @Min(value = 1)
                        @NotNull(message = "Event ID field must not be left null.")
                        long eventId) {
}
