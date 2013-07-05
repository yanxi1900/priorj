package exception;

/**
 * This class is an exception throw when a empty path is passed.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class EmptyPathException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public EmptyPathException(){
		super("Paths empty not permited!");
	}
}
