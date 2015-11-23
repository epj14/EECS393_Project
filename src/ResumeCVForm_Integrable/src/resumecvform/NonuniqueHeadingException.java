package resumecvform;
/**
 * a class that defines an exception to be used when a heading in a template is not unique
 */
public class NonuniqueHeadingException extends Exception {

	//default serialVersionUID
	private static final long serialVersionUID = 1L;

	/**
	 * constructs a new NonuniqueHeadingException 
	 * with null as its detailed message
	 */
	public NonuniqueHeadingException() {
		super();
	}
	
	/**
	 * constructs a new NonuniqueHeadingException with the specified detail message
	 * @param message the detail message
	 */
	public NonuniqueHeadingException(String message) {
		super(message);
	}
	
	/**
	 * constructs a new NonuniqueHeadingException with the specified detail message and 
	 * cause
	 * @param message the detail message
	 * @param cause the cause
	 */
	public NonuniqueHeadingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * constructs a new NonuniqueHeadingException with the specified cause and a detailed 
	 * message of (cause == null ? null : cause.toString())
	 * @param cause the cause
	 */
	public NonuniqueHeadingException(Throwable cause) {
		super(cause);
	}
	
}
