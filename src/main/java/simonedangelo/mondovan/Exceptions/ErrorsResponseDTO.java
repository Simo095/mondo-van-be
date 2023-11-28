package simonedangelo.mondovan.Exceptions;

import java.util.Date;
import java.util.List;

public record ErrorsResponseDTO(String msg, Date data, List<String> errorList) {
}
