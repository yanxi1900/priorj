package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	Employee e;

	@Before
	public void setUp() throws Exception {
		e = new Employee("samuel", 25, "09933302");
	}

	@After
	public void tearDown() throws Exception {
		e = null;
	}

	@Test
	public void testCreateEmployee() {
		assertEquals("samuel", e.getName());
		assertTrue(e.getAge()==25);
		assertEquals("09933302", e.getRegister());
	}

}
