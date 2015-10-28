package main;

/**
 * a class that defines an exception to be used when invalid templates are encountered
 */
public class InvalidTemplateException extends Exception {

	//default serialVersionUID
	private static final long serialVersionUID = 1L;

	/**
	 * constructs a new InvalidTemplateException 
	 * with null as its detailed message
	 */
	public InvalidTemplateException() {
		super();
	}
	
	/**
	 * constructs a new InvalidTemplateException 
	 * with the specified detail message
	 * @param message the detail message
	 */
	public InvalidTemplateException(String message) {
		super(message);
	}
	
	/**
	 * constructs a new InvalidTemplateException with the specified detail message 
	 * and cause
	 * @param message the detail message
	 * @param cause the cause
	 */
	public InvalidTemplateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * constructs a new InvalidTemplateException with the specified cause and a 
	 * detailed message of (cause == null ? null : cause.toString())
	 * @param cause the cause
	 */
	public InvalidTemplateException(Throwable cause) {
		super(cause);
	}
}
