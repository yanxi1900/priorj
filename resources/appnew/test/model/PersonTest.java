package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	Person person;
	
	@Before
	public void setUp() throws Exception {
		person = new Person();
	}

	@After
	public void tearDown() throws Exception {
		person = null;
	}

	@Test
	public void testCreatePerson() {
		person = new Person("samuel", 25);
		assertEquals("samuel", person.getName());
		assertTrue (person.getAge()==25);
	}
	
	@Test
	public void testUpdatePerson() {
		person = new Person("samuel", 25);
		assertEquals("samuel", person.getName());
		assertTrue (person.getAge()==25);
		
		person.setName("samuel", "santos");
		assertEquals("samuel", person.getName());
		
		person.setAge(22);
		assertTrue (person.getAge()==22);
		
	}
	

}
