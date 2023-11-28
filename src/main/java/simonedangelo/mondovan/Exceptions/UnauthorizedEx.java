package simonedangelo.mondovan.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class UnauthorizedEx extends RuntimeException {
    private List<ObjectError> errorList;

    public UnauthorizedEx(String message) {
        super(message);
    }

    public UnauthorizedEx(List<ObjectError> errorList) {
        this.errorList = errorList;
    }
}
