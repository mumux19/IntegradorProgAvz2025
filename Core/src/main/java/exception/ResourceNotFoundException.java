package exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msj) {
        super(msj);
    }
}
