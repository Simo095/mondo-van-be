package simonedangelo.mondovan.User.Payload;

import java.time.LocalDate;

public record UsersDTO(String name,
                       String surname,
                       String email,
                       String password,
                       LocalDate dayOfBirth,
                       long idTown,
                       String street,
                       int houseNumber,
                       int zipCode) {
}
