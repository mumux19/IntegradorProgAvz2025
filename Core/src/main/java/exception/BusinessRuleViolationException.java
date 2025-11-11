package exception;

public class BusinessRuleViolationException extends RuntimeException {
    public BusinessRuleViolationException(String msj) {
        super(msj);
    }
}
