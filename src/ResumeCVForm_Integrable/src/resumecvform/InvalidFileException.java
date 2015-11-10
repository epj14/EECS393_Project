package resumecvform;

/**
 * a class that defines an exception to be used when invalid templates are encountered
 */
public class InvalidFileException extends Exception {

	//default serialVersionUID
	private static final long serialVersionUID = 1L;

	/**
	 * constructs a new InvalidFileException 
	 * with null as its detailed message
	 */
	public InvalidFileException() {
		super();
	}
	
	/**
	 * constructs a new InvalidFileException with the specified detail message
	 * @param message the detail message
	 */
	public InvalidFileException(String message) {
		super(message);
	}
	
	/**
	 * constructs a new InvalidFileException with the specified detail message and cause
	 * @param message the detail message
	 * @param cause the cause
	 */
	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * constructs a new InvalidFileException with the specified cause and a detailed 
	 * message of (cause == null ? null : cause.toString())
	 * @param cause the cause
	 */
	public InvalidFileException(Throwable cause) {
		super(cause);
	}
}