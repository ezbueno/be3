package bueno.ezandro.exceptions;

public class IntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegrityViolationException(String message) {
		super(message);
	}

}