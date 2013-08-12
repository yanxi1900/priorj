package model;

import java.io.Serializable;
import java.lang.Exception;

/**
 * Esssa classe é uma exceção lançada quando a classe está vazia.
 *  
 * @author Renan
 * @author Samuel T. C. santos
 * @version 1.0
 *
 */
public class EmptyFieldException extends Exception implements Serializable {

	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public EmptyFieldException(){
		super("empty field not permitted!");
	}
}
