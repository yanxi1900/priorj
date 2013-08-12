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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;


/**
 * Tests for class TestCase.
 *  
 * @author Samuel T. C. Santos
 * @version 1.0
 * {@link }
 */
public class TestCaseTest {

	private TestCase testCase;
	private ClassCode classCode;
	private Method method1, method2;
	private Statement statement1, statement2, statement3;
	private Statement statement4, statement5, statement6;
	
	@Before
	public void setUp(){
		testCase = new TestCase("testA");
		classCode = new ClassCode("pkgA", "ClassA");
	
		statement1 = new Statement("22");
		statement2 = new Statement("3");
		statement3 = new Statement("11");
		statement4 = new Statement("5");
		statement5 = new Statement("150");
		statement6 = new Statement("3");
		
		method1 = new Method("m1");
			
		method1.addStatement(statement1);
		method1.addStatement(statement2);
		method1.addStatement(statement3);
		
		method2 = new Method("m2");
		method2.addStatement(statement4);
		method2.addStatement(statement5);
		method2.addStatement(statement6);
	
	}
	
	@Test
	public void testInitialization() {
		assertNotNull(testCase);
		assertEquals("testA", testCase.getName());
		assertEquals("ClassA", classCode.getName());
		assertEquals("m1" , method1.getName());
		assertEquals("m2", method2.getName());
	}
	
	@Test
	public void testAddClassCode(){
		assertNotNull(classCode);
		assertTrue(testCase.getClassCoverage().isEmpty());
		
		testCase.addClassCoverage(classCode);
		assertFalse(testCase.getClassCoverage().isEmpty());
		
		assertArrayEquals(new  String[] {"pkgA.ClassA"}, testCase.getClassCodeCoverage().toArray());
		
	}
	
	@Test
	public void testGetMethodCovered(){
		classCode.addMethod(method1);
		classCode.addMethod(method2);
		
		testCase.addClassCoverage(classCode);
		assertTrue(testCase.getMethodCoverage().size() == 2);
		assertArrayEquals(new String[] {"pkgA.ClassA.m1","pkgA.ClassA.m2"},testCase.getMethodCoverage().toArray());
	}
	
	@Test
	public void testGetMethodCoveredDistinct(){
		classCode.addMethod(method1);
		classCode.addMethod(method2);
		classCode.addMethod(method2);
		classCode.addMethod(method2);
		classCode.addMethod(method2);
		classCode.addMethod(method1);
		
		testCase.addClassCoverage(classCode);
		assertTrue(testCase.getMethodCoverage().size() == 6);
		assertTrue(testCase.getMethodCoverageDistinct().size() == 2);
		
		assertArrayEquals(new String[] {"pkgA.ClassA.m1","pkgA.ClassA.m2"},testCase.getMethodCoverageDistinct().toArray());
	}
	
	@Test
	public void testGetStatementsCovered(){
		
		assertFalse(method1.getStatementCoverage().isEmpty());
		assertFalse(method2.getStatementCoverage().isEmpty());
		
		classCode.addMethod(method1);
		classCode.addMethod(method2);
		
		testCase.addClassCoverage(classCode);
		
		assertFalse(testCase.getClassCoverage().isEmpty());
		
		List<String> stmt = testCase.getStatementsCoverage();
		
		assertFalse(stmt.isEmpty());
		
		String [] statementCovered = {"pkgA.ClassA.m1.22",
				                      "pkgA.ClassA.m1.3",
				                      "pkgA.ClassA.m1.11",
				                      "pkgA.ClassA.m2.5",
				                      "pkgA.ClassA.m2.150",
				                      "pkgA.ClassA.m2.3"};

		assertArrayEquals(statementCovered, stmt.toArray());
	}
	

}
