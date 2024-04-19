package nickdg.Java.Spring.Boot.security.project1.exceptions;

public class ExceptionUnauthorized extends RuntimeException {
    public ExceptionUnauthorized (String msg) {
        super(msg);
    }
}
