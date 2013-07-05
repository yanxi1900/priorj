package exception;

import java.lang.Exception;

/**
 * This classe is an exception throw when the user send
 * a project name empty.
 * 
 * @author Samuel T. C. dos Santos
 * @version 1.0
 */
public class EmptyPriorJProjectNameException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public EmptyPriorJProjectNameException(){
		super("Project name can not be empty");
	}
}

