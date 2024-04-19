package nickdg.Java.Spring.Boot.security.project1.payloads;

import jakarta.validation.constraints.*;

public record EventDTO(@NotEmpty(message = "Title field must not be left empty.")
                       @Size(min = 2, max = 30, message = "Title field length must be between 2 and 30 characters.")
                       String title,
                       @NotEmpty(message = "Description field must not be left empty.")
                       @Size(min = 2, max = 180, message = "Description field length must be between 2 and 180 characters.")
                       String description,
                       @NotEmpty(message = "Place field must not be left empty.")
                       @Size(min = 2, max = 30, message = "Place field length must be between 2 and 30 characters.")
                       String place,
                       @NotEmpty(message = "You must declare the event date (YYYY-MM-DD)")
                       @Pattern(regexp = "/[1-9][0-9][0-9]{2}-([0][1-9]|[1][0-2])-([1-2][0-9]|[0][1-9]|[3][0-1])/gm", message = "The date must be in the YYYY-MM-DD format.")
                       String date,
                       @Min(value = 3, message = "Minimum participants number: 3.")
                       @Max(value = 15000, message = "Maximum participants number: 15000.")
                       @NotNull(message = "Capacity field must not be left null.")
                       int maxCapacity,
                       @Min(value = 1)
                       @NotNull(message = "Organizer ID field must not be left null.")
                       long organizerId) {
}
