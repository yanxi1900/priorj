package prioritization;

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
import input.InputParse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.TechniquesEnum;
import util.PathTo;
import coverage.TestCase;
import exception.EmptySetOfTestCaseException;


/** 
 * This class verify the prioritization results.
 * 
 * @author Samuel T. C. Santos.
 * @version 1.0
 */
public class PrioritizationTest {

	private Prioritization prioritize;
	private List<TestCase> tests;
	private String path;
	private String filename;
	
	@Before
	public void setUp(){
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR;
		filename = "xml-security.txt";
		path += filename;
		
		InputParse coverage = new InputParse(path, "testsuite");
		coverage.runParse();
	
		tests = coverage.getResultAsTestCase();	
	}
	
	@After
	public void setDown(){
		tests = null;
		prioritize = null;
	}
	
	@Test
	public void testInitialization() {
		assertNotNull(prioritize);
	}
	
	@Test
	public void testTotalTestBeforeAndAfterPrioritizationUsingRandom() throws EmptySetOfTestCaseException{
		
		prioritize = new Prioritization(tests,TechniquesEnum.Random.getId() , "", "");
		
		prioritize.prioritize();
		
		List<String> result = prioritize.assignWeight();
		
		assertTrue(tests.size() == result.size());
	}
	
	@Test
	public void testTotalTestBeforeAndAfterPrioritizationUsingTMC() throws EmptySetOfTestCaseException{
		prioritize = new Prioritization(tests,TechniquesEnum.TOTAL_METHOD_COVERAGE.getId() , "", "");
		prioritize.prioritize();
		List<String> result = prioritize.assignWeight();
		assertTrue(tests.size() == result.size());
	}
	
	@Test
	public void testTotalTestBeforeAndAfterPrioritizationUsingTSC() throws EmptySetOfTestCaseException{
		prioritize = new Prioritization(tests,TechniquesEnum.TOTAL_STATEMENT_COVERAGE.getId() , "", "");
		prioritize.prioritize();
		List<String> result = prioritize.assignWeight();
		assertTrue(tests.size() == result.size());
	}
		
	@Test
	public void testTotalTestBeforeAndAfterPrioritizationUsingAMC() throws EmptySetOfTestCaseException{
		prioritize = new Prioritization(tests,TechniquesEnum.ADDITIONAL_METHOD_COVERAGE.getId() , "", "");
		prioritize.prioritize();
		List<String> result = prioritize.assignWeight();
		assertTrue(tests.size() == result.size());
	}
	
	@Test
	public void testTotalTestBeforeAndAfterPrioritizationUsingASC() throws EmptySetOfTestCaseException{
		prioritize = new Prioritization(tests,TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE.getId() , "", "");
		prioritize.prioritize();
		List<String> result = prioritize.assignWeight();
		assertTrue(tests.size() == result.size());
	}
	
	
}
