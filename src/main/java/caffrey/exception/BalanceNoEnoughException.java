package caffrey.exception;

public class BalanceNoEnoughException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BalanceNoEnoughException() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("Óà¶î²»×ã");
	}

	public BalanceNoEnoughException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BalanceNoEnoughException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BalanceNoEnoughException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BalanceNoEnoughException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
