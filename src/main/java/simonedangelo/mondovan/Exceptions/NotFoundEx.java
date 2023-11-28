package simonedangelo.mondovan.Exceptions;

import lombok.Getter;

@Getter
public class NotFoundEx extends RuntimeException {

    public NotFoundEx(String message) {
        super(message);
    }
}
