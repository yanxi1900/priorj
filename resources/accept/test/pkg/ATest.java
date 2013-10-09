package pkg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ATest {

	A a;
	
	@Before
	public void setUp() throws Exception {
		a = new A();
	}

	@After
	public void tearDown() throws Exception {
		a = null;
	}

	@Test
	public void testM1() {
		assertEquals(1, a.m1());
		
	}
	
	@Test
	public void testM2() {
		assertEquals("3", a.m2(1,2));
	}
	

}