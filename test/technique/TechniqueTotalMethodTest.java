package technique;

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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.TestCase;
import exception.EmptySetOfTestCaseException;


/**
 * This class implements the Total Statement Technique.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class TechniqueTotalMethodTest {

	private TechniqueTotalMethod tmc;
	
	private List<TestCase> testsList;	
	
	private TestCase [] tests = {
			 new TestCase("testA"),
			 new TestCase("testB"), 
			 new TestCase("testC"),
			 new TestCase("testD"),
			 new TestCase("testE")
			};
	
	private ClassCode [] classes = {
			new ClassCode("pkg","A"),
            new ClassCode("pkg","B")
			};
	
	private Method[] methods = { 
			new Method ("m1") ,
		    new Method("m2"),
		    new Method("m3"),
		    new Method("m4"),
		    new Method("m5"),
		    new Method("m6"),
		    new Method("m7"),
			};
	
	@Before
	public void setUp(){
		testsList = new LinkedList<TestCase>();
		tmc = new TechniqueTotalMethod(testsList);
	}
	
	@After
	public void setDown(){
		testsList = null;
		tmc = null;
	}
	

	@Test
	public void testInitialization() {
		assertNotNull(tmc);
		assertNotNull(testsList);
	}
	
	@Test (expected = EmptySetOfTestCaseException.class)
	public void prioritizationEmptySetTestCase() throws EmptySetOfTestCaseException{
		List<String> prioritizedList = tmc.assingWeight();
		
		assertArrayEquals( new  String[]{}, prioritizedList.toArray());
	}
	
	@Test
	public void prioritizationOneTestCase() throws EmptySetOfTestCaseException{
		
		
		//class A and method m1 
		classes[0].addMethod(methods[0]);
		//classsB and method m2
		classes[1].addMethod(methods[1]);
		
		//test case A
		tests[0].addClassCoverage(classes[0]);
		tests[0].addClassCoverage(classes[1]);
		
		tests[0].setSignature("pkg.A.testA");
		
		testsList.add(tests[0]);
		
		
		tmc = new TechniqueTotalMethod(testsList);
		
		List<String> prioritized = tmc.assingWeight();
	
		System.out.println(prioritized);
		Assert.assertEquals("[p1.A.testA]", prioritized.toString());
	}
	
	@Test
	public void prioritizationEveryTestCase() throws EmptySetOfTestCaseException{
				
		//classA cover the method m1,m2,m3,m4
		classes[0].addMethod(methods[0]);
		classes[0].addMethod(methods[1]);
		classes[0].addMethod(methods[2]);
		classes[0].addMethod(methods[3]);
		
		//class B cover m4, m5
		classes[1].addMethod(methods[4]);
		
		//testA cover class A
		tests[0].addClassCoverage(classes[0]);
		//set the signature
		tests[0].setSignature("pkg.A.testA");
		
		//calculate and set
		int numberStmtCoverage = tests[0].getNumberMethodsCoveredDistinct();
		tests[0].setNumberStatementsCoverageDistinct(numberStmtCoverage);
		
		tests[1].addClassCoverage(classes[1]);
		tests[1].setSignature("pkg.B.TestB");
		
		//calculate and set
		numberStmtCoverage = tests[0].getNumberMethodsCoveredDistinct();
		tests[1].setNumberStatementsCoverageDistinct(numberStmtCoverage);
		
		testsList.add(tests[0]);
		testsList.add(tests[1]);
		
		tmc = new TechniqueTotalMethod(testsList);
		
		List<String> prioritized = tmc.assingWeight();
	
		Assert.assertEquals("[pkg.A.testA, pkg.B.TestB]", prioritized.toString());
		
	}
	

}
