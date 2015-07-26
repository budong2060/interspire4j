package hu.bankmonitor.commons.interspire4j;

public class InterspireException extends Exception {

	private static final long serialVersionUID = 1L;

	public InterspireException() {
		super();
	}

	public InterspireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InterspireException(String message, Throwable cause) {
		super(message, cause);
	}

	public InterspireException(String message) {
		super(message);
	}

	public InterspireException(Throwable cause) {
		super(cause);
	}

}
