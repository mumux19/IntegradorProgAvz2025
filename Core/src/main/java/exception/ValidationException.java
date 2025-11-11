package exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msj) {
        super(msj);
    }
}
