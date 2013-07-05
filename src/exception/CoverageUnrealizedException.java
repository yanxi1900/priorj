package exception;
/**
 * 
 * This class is an exception throw when the coverage is not done.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CoverageUnrealizedException extends Exception {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public CoverageUnrealizedException(){
		super("Coverage Unrealized!");
	}
	/**
	 * An error message.
	 * 
	 * @param errorMessage
	 * 		error message.
	 */
	public CoverageUnrealizedException(String errorMessage){
		super("Coverage Unrealized: " + errorMessage);
	}
	
	
}
