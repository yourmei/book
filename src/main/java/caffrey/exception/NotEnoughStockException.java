package caffrey.exception;

public class NotEnoughStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughStockException() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("��治��!!");
	}

	public NotEnoughStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
