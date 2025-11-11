package exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String msj) {
        super(msj);
    }
}
