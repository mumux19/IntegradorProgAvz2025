package exception;

public class TaskUseCaseException extends RuntimeException {
    public TaskUseCaseException(String message) {
        super(message);
    }
}
