package exception;

/**
 * This class is an exception throw when the instrumentation process is not performed.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class InstrumentationUnrealizedException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public InstrumentationUnrealizedException(){
		super("Instrumentation Unrealized!");
	}
	
	public InstrumentationUnrealizedException(String errorMessage){
		super("Instrumentation Unrealized: " + errorMessage);
	}
	
}
