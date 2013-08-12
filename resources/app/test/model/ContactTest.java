package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContactTest {

	Contact c;
	
	@Before
	public void setUp() throws Exception {
		c = new Contact();
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testCreateContact() {
		c = new Contact("samuel", 1988);
		
		assertEquals("samuel", c.getName());
		assertTrue( c.getAge() == 25);
	}

}
