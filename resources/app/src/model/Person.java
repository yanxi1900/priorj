package model;

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
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
