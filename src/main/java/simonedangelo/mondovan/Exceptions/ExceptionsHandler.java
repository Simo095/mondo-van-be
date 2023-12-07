package simonedangelo.mondovan.Exceptions;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    //RICHIESTE NON AUTORIZZATE
    @ExceptionHandler(UnauthorizedEx.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsResponseDTO errUnauthorized(UnauthorizedEx ex) {
        if (ex.getErrorList() != null) {
            List<String> errorList = ex.getErrorList().stream().map(e -> e.getDefaultMessage()).toList();
            return new ErrorsResponseDTO(ex.getMessage(), new Date(), errorList);
        } else {
            return new ErrorsResponseDTO(ex.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO errNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    //RICHIETE ERRATE
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO erroBadRequest(BadRequestEx ex) {
        if (ex.getErrorList() != null) {
            List<String> listaErrori = ex.getErrorList().stream().map(e -> e.getDefaultMessage()).toList();
            return new ErrorsResponseDTO(ex.getMessage(), new Date(), listaErrori);
        } else {
            return new ErrorsResponseDTO(ex.getMessage(), new Date(), new ArrayList<>());
        }
    }

    //RICHIESTE NON TROVATE
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO errNotFound(NotFoundEx ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerJSONFormat(HttpMessageNotReadableException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    //CONTROLLLA CHE SIA LA MODALITA DI INVIO GIUSTA QUINDI ROW E JSON E RITORNA LA FRONT END L'ERRORE
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerJSONTrue(HttpMediaTypeNotSupportedException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    //MAX UPLOAD SIZE ERROR
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerFileSize(MaxUploadSizeExceededException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerNullerPointer(NullPointerException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerUnexpected(UnexpectedTypeException ex) {
        return new ErrorResponseDTO(ex.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handlerGenricError(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponseDTO("INTERNAL ERROR...", new Date());

    }
}
