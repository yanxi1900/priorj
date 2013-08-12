package model;

import java.io.Serializable;

/**
 * Exceção lançada quando um número é invalido.
 * 
 * @author Renan
 * @autrho Samuel T. C. Santos
 *
 */
public class InvalidNumberException extends Exception implements Serializable {
	
	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public  InvalidNumberException(){
		super("Invalid Number");
	}
	
}
