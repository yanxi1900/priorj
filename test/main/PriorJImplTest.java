package main;

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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;

import project.JUnitVersionEnum;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.EmptySetOfTestCaseException;
import exception.InstrumentationUnrealizedException;

import technique.TechniquesEnum;
import util.Settings;


/**
 * This class is a test unit class to validate service in the PriorJImpl class.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class PriorJImplTest {

	private PriorJ priorj;
	
	private String app = "";
	private String code ="";
	private String lib = "";
	private String test = "";
	private String codeNew = "";
	
	private final String separator = System.getProperty("file.separator");
	
		
	@Before
	public void setUp(){
		priorj = new PriorJImpl();	
	}
	
	@After
	public void setDown(){
		priorj = null;
	}
	
	@Test
	public void testInitializationPriorJImpl(){
		assertNotNull(priorj);
		assertEquals("", priorj.getPathApplication());
		assertEquals("", priorj.getPathCode());
		assertEquals("", priorj.getPathLibraries());
		assertEquals("", priorj.getPathTests());
		assertEquals("", priorj.getPathCodeNew());
		
		assertFalse(priorj.isInstrumented());
		assertFalse(priorj.isCovered());
		assertFalse(priorj.isReadLog());
		assertFalse(priorj.isPrioritized());
	}
	
	@Test
	public void testPathAppBeforeInit(){
		priorj.setPathApplication("myapp");
		assertEquals("myapp" , priorj.getPathApplication());
	}
	
	@Test
	public void testPathAppAfterInit(){
		priorj.setPathApplication("myapp");
		
		priorj.initPriorJ();
		
		assertEquals("myapp" , priorj.getPathApplication());	
	}
		
	@Test
	public void testPathCodeBeforeInit(){
		priorj.setPathCode("myapp" + separator + "src");
		assertEquals("myapp" + separator + "src", priorj.getPathCode());
	}
	
	@Test
	public void testPathCodeAfterInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathCode("myapp" + separator + "src");
		priorj.initPriorJ();
		assertEquals("src", priorj.getPathCode());
	}
	
	@Test
	public void testPathLibBeforeInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathLibraries("myapp" + separator + "lib");
		assertEquals("myapp" + separator + "lib", priorj.getPathLibraries());
	}
	
	@Test
	public void testPathLibAfterInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathLibraries("myapp" + separator + "lib");
		priorj.initPriorJ();
		
		assertEquals("lib", priorj.getPathLibraries());
	}
	
	@Test
	public void testPathTestBeforeInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathTests("myapp" + separator + "test");
		assertEquals("myapp" + separator + "test", priorj.getPathTests());
	}
	
	@Test
	public void testPathTestAfterInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathTests("myapp" + separator + "test");
		priorj.initPriorJ();
		
		assertEquals("test", priorj.getPathTests());
	}
	
	@Test
	public void testPathNewBeforeInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathCodeNew("myappnewcode" + separator + "src");
		assertEquals("myappnewcode" + separator + "src", priorj.getPathCodeNew());
	}
	
	@Test
	public void testPathNewAfterInit(){
		priorj.setPathApplication("myapp");
		priorj.setPathCodeNew("myappnewcode" + separator + "src");
		priorj.initPriorJ();
		
		assertEquals("myappnewcode" + separator + "src", priorj.getPathCodeNew());
	}
	
	
	@Test
	public void testPriorJRunInstrumentation() throws InstrumentationUnrealizedException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		
		priorj.runInstrumentation();
		
		assertTrue(priorj.isInstrumented());
	}
	
	@Test
	public void testPriorJRunCoverage() throws InstrumentationUnrealizedException, CoverageUnrealizedException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		
		assertTrue(priorj.isCovered());
	}
	
	@Test
	public void testPriorJRunReadLog() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		assertTrue(priorj.isReadLog());
	}
	
	@Test
	public void testPriorJRunPrioritizationTMC() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException, EmptySetOfTestCaseException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		List<TestCase> tests = priorj.getTestCases();
		
		List<String> prioritizedList = priorj.runPrioritizationTMC(tests);
		
		
		assertTrue(priorj.isReadLog());
		assertFalse(prioritizedList.isEmpty());
	}
	
	@Test
	public void testPriorJRunPrioritizationTSC() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException, EmptySetOfTestCaseException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		List<TestCase> tests = priorj.getTestCases();
		
		List<String> prioritizedList = priorj.runPrioritizationTSC(tests);
		
		
		assertTrue(priorj.isReadLog());
		assertFalse(prioritizedList.isEmpty());
	}
	
	@Test
	public void testPriorJRunPrioritizationAMC() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException, EmptySetOfTestCaseException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		List<TestCase> tests = priorj.getTestCases();
		
		List<String> prioritizedList = priorj.runPrioritizationAMC(tests);
		
		
		assertTrue(priorj.isReadLog());
		assertFalse(prioritizedList.isEmpty());
	}

	@Test
	public void testPriorJRunPrioritizationASC() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException, EmptySetOfTestCaseException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		List<TestCase> tests = priorj.getTestCases();
		
		List<String> prioritizedList = priorj.runPrioritizationASC(tests);
		
		
		assertTrue(priorj.isReadLog());
		assertFalse(prioritizedList.isEmpty());
	}
	
	@Test
	public void testPriorJRunPrioritizationRND() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException, EmptySetOfTestCaseException{
		priorj.setPathApplication(Settings.APP);
		priorj.setPathCode(Settings.APP_CODE);
		priorj.setPathLibraries(Settings.APP_LIB);
		priorj.setPathTests(Settings.APP_TEST);
		priorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
		
		priorj.runInstrumentation();
		priorj.runCoverage();
		priorj.runReadLog();
		
		List<TestCase> tests = priorj.getTestCases();
		
		List<String> prioritizedList = priorj.runPrioritizationRD(tests);
				
		assertTrue(priorj.isReadLog());
		assertFalse(prioritizedList.isEmpty());
	}


	@Test
	public void testPrioritizedTestPositionByTechnique(){
		String filename = "TMC.txt";
		
		String path = Settings.RESOURCES_FILES + Settings.SEPARATOR + filename;
		String localPath = Settings.ORDER;
		
		String testCaseName = "tests.AVLTest.testEstadoInicial";
		
		TechniquesEnum technique = TechniquesEnum.TOTAL_METHOD_COVERAGE;
		
		int position = priorj.getPrioritizedTestPositionByTechnique(localPath,testCaseName, technique);
		
		assertTrue(position == 6);
		
		testCaseName = "tests.AVLTest.testAnyThing";
		
		position = priorj.getPrioritizedTestPositionByTechnique(localPath,testCaseName, technique);
		
		assertTrue(position == -1);
				
	}
	
	
}
