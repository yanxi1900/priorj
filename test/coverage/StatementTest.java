package coverage;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
