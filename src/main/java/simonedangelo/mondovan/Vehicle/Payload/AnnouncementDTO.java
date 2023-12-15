package simonedangelo.mondovan.Vehicle.Payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnnouncementDTO(@NotNull @Size(max = 1000) String announcement) {
}
