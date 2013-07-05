package exception;

/**
 * This class is an exception throw when the user 
 * to try create a new project with same name of one
 * which already exist.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class DuplicateProjectNameException extends Exception {
    
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateProjectNameException(){
		super("There is a project with that name!");
	}
}
