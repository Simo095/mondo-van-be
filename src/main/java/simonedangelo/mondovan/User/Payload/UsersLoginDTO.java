package simonedangelo.mondovan.User.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UsersLoginDTO(@NotNull(message = "the email field is null")
                            @NotEmpty(message = "the email field is empty")
                            String email,
                            @NotNull(message = "the password field is null")
                            @NotEmpty(message = "the password field is empty")
                            String password) {
}
