package exception;

/**
 * This class is a exception throw when can not read log file.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CannotReadLogFileException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public CannotReadLogFileException(){
		super("Coverage Unrealized!");
	}
	/**
	 * An error message.
	 * 
	 * @param errorMessage
	 * 		error message.
	 */
	public CannotReadLogFileException(String errorMessage){
		super("Coverage Unrealized: " + errorMessage);
	}
	
}
