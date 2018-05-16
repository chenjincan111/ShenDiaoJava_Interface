package fthink.exception;

public class ServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3863531039192504585L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String code, String error) {
		super();
	}

}
