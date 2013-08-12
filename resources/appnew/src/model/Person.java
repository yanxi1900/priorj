package model;

import java.util.Calendar;

/**
 * <p>
 * 		This class represent a person with name and age.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class Person {

	protected String name;
	protected int age;
	protected int birthyear;
	
	/**
	 * Constructor
	 * @param name
	 * @param age
	 */
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name, String sobrenome) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int calculateAge(int birthyear){
		this.birthyear = birthyear;
		return Calendar.getInstance().get(Calendar.YEAR) - birthyear;
	}

}
