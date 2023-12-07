package simonedangelo.mondovan.Reservation.Payload;

import java.time.LocalDate;

public record ReservationsDTO(LocalDate start,
                              LocalDate end

) {
}
