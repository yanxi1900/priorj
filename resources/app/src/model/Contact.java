package model;

import java.util.Calendar;

/**
 * <p>
 * 		This class represent a contact with birthday data.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class Contact extends Person {

	protected int birthyear;
	
	/**
	 * Constructor
	 * @param name
	 * @param birthyear
	 */
	public Contact(String name, int birthyear) {
		super(name, 0);
		// TODO Auto-generated constructor stub
		setAge(calculateAge(birthyear));
	}
	
	public Contact(){
		
	}
	
	public int calculateAge(int birthyear){
		this.birthyear = birthyear;
		return Calendar.getInstance().get(Calendar.YEAR) - birthyear;
	}
}
