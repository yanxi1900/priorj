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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.Method;
import coverage.Statement;

/***
 * Tests Cases for class Method.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class MethodTest {

	private Method mA, mB;
	
	@Before
	public void setUp(){
		mA = new Method("methodA");
		mB = new Method("methodB");
	}
	
	@After
	public void tearDown(){
		mA = null;
		mB = null;
	}
	
	@Test
	public void testInitialization() {
		assertEquals("methodA", mA.getName());
		assertEquals("methodB", mB.getName());
		
		assertTrue(mA.getNumberStatements() == 0);
		assertTrue(mB.getNumberStatements() == 0);
	}
	
	@Test
	public void testEquals(){
		assertFalse(mA.equals(mB));
	}

	@Test 
	public void testAddStatement(){
		assertTrue(mA.getNumberStatements() == 0);
		
		mA.addStatement(new Statement("13"));
		mA.addStatement(new Statement("16"));
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("21"));
		mA.addStatement(new Statement("37"));
		
		assertTrue(mA.getNumberStatements() == 5);
	}
	
	
	@Test
	public void testNumberStatements(){
		assertTrue(mA.getNumberStatements() == 0);
		assertTrue(mB.getNumberStatements() == 0);		
	}
	
	@Test
	public void testUniqueStatements(){
		assertTrue(mA.getNumberStatements() == 0);
		
		mA.addStatement(new Statement("13"));
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("21"));
		mA.addStatement(new Statement("17"));
		
		assertTrue(mA.getUniqueNumberStatements() == 3);
	}
	
	@Test
	public void testListStatementCoverage(){
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("21"));
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("23"));
		
		List<Statement> stmts = mA.getStatementCoverage();
		
		assertEquals(4, stmts.size());
	}
	
	@Test
	public void testListUniqueStatementCoverage(){
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("21"));
		mA.addStatement(new Statement("17"));
		mA.addStatement(new Statement("23"));
		
		List<Statement> stmts = mA.getUniqueStatements();
		
		assertEquals(3, stmts.size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNullStatement(){
		mA.addStatement(null);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testCreateMethodEmptyName(){
		mA = new Method("");
	}
	
	
	@Test (expected = IllegalArgumentException.class )
	public void testCreateMethodWithNullName(){
		mA = new Method(null);
	}
	
	
}
