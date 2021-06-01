package bueno.ezandro.exceptions;

import java.util.List;

public class ErrorResponseException {

	private final String message;
	private final int code;
	private final String status;
	private final String objectName;
	private final List<ErrorObjectException> errors;

	public ErrorResponseException(String message, int code, String status, String objectName, List<ErrorObjectException> errors) {
		this.message = message;
		this.code = code;
		this.status = status;
		this.objectName = objectName;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getObjectName() {
		return objectName;
	}

	public List<ErrorObjectException> getErrors() {
		return errors;
	}

}