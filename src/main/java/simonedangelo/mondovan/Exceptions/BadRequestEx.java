package simonedangelo.mondovan.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestEx extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestEx(String message) {
        super(message);
    }

    public BadRequestEx(List<ObjectError> errorList) {
        this.errorList = errorList;
    }
}
