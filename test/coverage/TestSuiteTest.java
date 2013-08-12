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

public class TestSuiteTest {
	
	private TestSuite suite;
	
	@Before
	public void setUp(){
		suite = new TestSuite("pkg", "ClassA");
	}
	

	@After
	public void tearDown(){
		suite = null;
	}
	
	@Test
	public void testInitialization(){
		assertTrue(suite.getNumberOfSuites() == 0);
	}
	
	@Test
	public void testSuitePackage(){
		assertEquals("pkg", suite.getPackageName());
	}
	
	@Test
	public void testSuiteClass(){
		assertEquals("ClassA", suite.getName());
	}
	
	@Test
	public void testSuiteSignature(){
		assertEquals("pkg.ClassA", suite.toString());
	}
	
	@Test
	public void testSuiteAddTestCase(){
		suite.addTestCase(new TestCase("a"));
		
		assertTrue(suite.getNumberOfSuites()== 1);
	}
	
	@Test
	public void testSuiteAddTestCaseNull(){
		suite.addTestCase(null);
		
		assertTrue(suite.getNumberOfSuites()== 0);
	}
}