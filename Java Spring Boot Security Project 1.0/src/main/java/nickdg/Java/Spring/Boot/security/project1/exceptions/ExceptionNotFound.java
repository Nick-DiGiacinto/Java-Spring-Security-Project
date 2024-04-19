package nickdg.Java.Spring.Boot.security.project1.exceptions;

public class ExceptionNotFound extends RuntimeException {
    public ExceptionNotFound(long id) {
        super("The element with ID '" + id + "' was not found.");
    }

    public ExceptionNotFound(String msg) {
        super(msg);
    }
}