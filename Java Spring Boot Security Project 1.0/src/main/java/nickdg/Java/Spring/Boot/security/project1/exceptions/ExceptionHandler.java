package nickdg.Java.Spring.Boot.security.project1.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import nickdg.Java.Spring.Boot.security.project1.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionBadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(ExceptionBadRequest e) {
        if (e.getErrorList() != null) {
            String msg = e.getErrorList().stream().map(err -> err.getDefaultMessage())
                    .collect(Collectors.joining(" "));
            return new ErrorResponseDTO(msg, LocalDateTime.now());
        }
        else return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGenericError(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDTO("Internal server error.", LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(ExceptionNotFound e) {
        return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleMissingRequestParameter(MissingServletRequestParameterException e) {
        return new ErrorResponseDTO(e.getMessage() + ".", LocalDateTime.now());
    }
}