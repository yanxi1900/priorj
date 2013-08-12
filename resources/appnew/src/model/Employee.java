package model;

/**
 * <p>
 * 		This class represent a employee.
 * </p>
 * @author Samuel T. C. Santos
 *
 */
public class Employee extends Person {

	private String register;
	
	/**
	 * Constructor
	 * @param name
	 * @param age
	 * @param register
	 * 		register number.
	 */
	public Employee(String name, int age, String register) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.register = register;
	}
	
	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
