package exception;

import java.lang.Exception;

public class EmptySetOfTestCaseException extends Exception {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public EmptySetOfTestCaseException(){
		super("Project Name is Empty!");
	}
	
}
