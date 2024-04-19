package nickdg.Java.Spring.Boot.security.project1.exceptions;

import java.util.List;
import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class ExceptionBadRequest extends RuntimeException {
    private List<ObjectError> errorList;

    public ExceptionBadRequest(String msg) {
        super(msg);
    }

    public ExceptionBadRequest(List<ObjectError> errorList) {
        super("Validation errors on payload.");
        this.errorList = errorList;
    }
}