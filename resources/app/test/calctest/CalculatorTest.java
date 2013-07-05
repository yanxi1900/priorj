package calctest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import calc.Calculator;

public class CalculatorTest {

	private Calculator calc;
	
	@Before
	public void setUp() throws Exception {
		calc = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
		calc = null;
	}

	@Test
	public void testAdd() {
		assertTrue(4 == calc.add(2, 2));
	}
	
	@Test
	public void testSub() {
		assertTrue(2.0 == calc.sub(2.0, 0.0));
	}
	
	@Test
	public void testMul() {
		assertTrue(4.0 == calc.mul(2, 2));
	}
	
	@Test
	public void testDiv() {
		assertTrue(1 == calc.div(2, 2));
	}
	
	@Test
	public void testMod() {
		assertTrue(0 == calc.mod(2, 2));
	}
	

}
