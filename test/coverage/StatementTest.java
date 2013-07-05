package coverage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.Statement;

/**
 * This class is an test for class Statement.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class StatementTest {

	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	
	@Before
	public void setUp(){
		stmt1 = new Statement("10");
		stmt2 = new Statement("15");
		stmt3 = new Statement("10");
	}
	
	@After
	public void tearDown(){
		stmt1 = null;
		stmt2 = null;
		stmt3 = null;
	}
	
	@Test
	public void testLineNumber() {
		assertEquals("10", stmt1.getLineNumber());
		assertEquals("15", stmt2.getLineNumber());
		
		stmt1.setLineNumber("30");
		stmt2.setLineNumber("56");
		
		assertEquals("30", stmt1.getLineNumber());
		assertEquals("56", stmt2.getLineNumber());
	}
	
	@Test
	public void testEquals(){
		assertTrue(stmt1.equals(stmt3));
		assertFalse(stmt2.equals(stmt1));
		assertTrue(stmt1.equals(stmt1));
	}
	
	@Test
	public void testCompareTo(){
		assertTrue(stmt1.compareTo(stmt2) < 0);
		assertTrue(stmt1.compareTo(stmt3) == 0);
	}

	@Test (expected = IllegalArgumentException.class )
	public void testCreateEmptyStatement(){
		stmt1 = new Statement("");
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void testCreateNullStatement(){
		stmt1 = new Statement(null);
	}
	
	@Test
	public void testToString(){
		assertEquals("10", stmt1.toString());
		assertEquals("15", stmt2.toString());
		assertEquals("10", stmt3.toString());
	}
	
}
