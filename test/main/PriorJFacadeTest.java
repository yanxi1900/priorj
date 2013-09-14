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

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.PriorJProject;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;
import exception.InstrumentationUnrealizedException;
import main.PriorJFacade;
import util.PathTo;


/**
 * Test Facade for using this application.
 * 
 * @author Samuel T. C. dos Santos
 * @version 1.0
 */
public class PriorJFacadeTest {
	
	private PriorJFacade facade;
	/**
	 * Path not exist.
	 */
	private final String pathNotFound = "xpto" + PathTo.SEPARATOR + "project";
	/**
	 * Path empty.
	 */
	private final String pathEmpty = "";
	
	@Before
	public void setUp(){
		facade = new PriorJFacade();
	}
	
	@After
	public void setDown(){
		facade = null;
	}
	
	@Test (expected = Exception.class )
	public void testPathApplicationEmpty() throws Exception{
		facade.setPathApp(pathEmpty);
	}
	
	@Test  (expected = Exception.class )
	public void testPathApplicationNotFound() throws Exception{
		facade.setPathApp(pathNotFound);
	}
	
	@Test (expected = Exception.class)
	public void testPathCodeEmpty() throws Exception{
		facade.setPathCode(pathEmpty);
	}
	
	@Test  (expected = Exception.class )
	public void testPathCodeNotFound() throws Exception{
		facade.setPathCode(pathNotFound);
	}
	
	@Test  // this case is permitted, project don't have libraries
	public void testPathLibIsEmpty() throws Exception{
		facade.setPathLib("");
		assertEquals("", facade.getPathLib());
	}

	
	@Test (expected = Exception.class)
	public void testPathTestEmpty() throws Exception{
		facade.setPathTest(pathEmpty);
	}
	
	@Test (expected = Exception.class)
	public void testPathTestDirectoryNotFound() throws Exception{
		facade.setPathTest(pathNotFound);
	}
	
	@Test
	public void testJUnitVersion(){
			
		assertEquals("junit3", facade.getJUnitVersion());
		
		facade.setJUnitVersion("junit4");
		
		assertEquals("junit4", facade.getJUnitVersion());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testJUnitVersionErrorInvalidVersionName(){
		facade.setJUnitVersion("junit3.x");
	}
	

	@Test (expected = InstrumentationUnrealizedException.class)
	public void testTryRunWithoutJUnitVersion() throws InstrumentationUnrealizedException{
		facade.runInstrumentation();
	}
	
	@Test
	public void testFacadeRunInstrumentation() throws Exception{
		facade.setPathApp(PathTo.APP);
		facade.setPathCode(PathTo.APP_CODE);
		facade.setPathLib(PathTo.APP_LIB);
		facade.setPathTest(PathTo.APP_TEST);
		
		facade.setJUnitVersion("junit4");
		
		facade.runInstrumentation();
		
		assertTrue(facade.isInstrumented());
	}
	
	
	@Test
	public void testFacadeRunCoverage() throws Exception{
		facade.setPathApp(PathTo.APP);
		facade.setPathCode(PathTo.APP_CODE);
		facade.setPathLib(PathTo.APP_LIB);
		facade.setPathTest(PathTo.APP_TEST);
		
		facade.setJUnitVersion("junit4");
		
		facade.runInstrumentation();
		facade.runCoverage();
		
		assertTrue(facade.isCovered());
	}
	
	@Test
	public void testFacadeRunPrioritizationChangedBlocks() throws Exception{
		facade.setPathApp(PathTo.APP);
		facade.setPathCode(PathTo.APP_CODE);
		facade.setPathLib(PathTo.APP_LIB);
		facade.setPathTest(PathTo.APP_TEST);
		facade.setPathCodeNew(PathTo.APP_CODE_NEW);
		
		facade.setJUnitVersion("junit4");
		
		facade.runInstrumentation();
		facade.runCoverage();
		facade.runReadLog();
		
		facade.addTechnique("cb");
		
		facade.runPrioritization();
		
		assertTrue(facade.isPrioritized());
	}
	
	
	@Test
	public void testFacadeRunReadLog() throws Exception{
		facade.setPathApp(PathTo.APP);
		facade.setPathCode(PathTo.APP_CODE);
		facade.setPathLib(PathTo.APP_LIB);
		facade.setPathTest(PathTo.APP_TEST);
		
		facade.setJUnitVersion("junit4");
		
		facade.runInstrumentation();
		facade.runCoverage();
		facade.runReadLog();
		
		assertTrue(facade.isReadLog());
	}
	
	
	@Test
	public void testFacadeRunPrioritization() throws Exception{
		facade.setPathApp(PathTo.APP);
		facade.setPathCode(PathTo.APP_CODE);
		facade.setPathLib(PathTo.APP_LIB);
		facade.setPathTest(PathTo.APP_TEST);
		
		facade.setJUnitVersion("junit4");
		
		facade.runInstrumentation();
		facade.runCoverage();
		facade.runReadLog();
		
		facade.addTechnique("tmc");
		
		facade.runPrioritization();
		
		assertTrue(facade.isPrioritized());
	}
	
	@Test
	public void testFacadeCreateProject() throws Exception{
		if ( facade.searchProject("console"))
			facade.removeProject("console");
		
		facade.createProject("console","junit4");
		
		assertTrue(facade.searchProject("console"));
		
	}
	
	@Test
	public void testFacadeSearchProject() throws Exception{
		if ( facade.searchProject("a"))
			facade.removeProject("a");
		
		if ( facade.searchProject("b"))
			facade.removeProject("b");
		
		if ( facade.searchProject("c"))
			facade.removeProject("c");
		
		facade.createProject("a","junit4");
		facade.createProject("b","junit4");
		facade.createProject("c","junit4");
		
		assertTrue(facade.searchProject("c"));
		assertTrue(facade.searchProject("b"));
		assertTrue(facade.searchProject("a"));
		assertFalse(facade.searchProject("x"));
		
	}
	
	
	@Test
	public void testFacadeRemoveProject() throws Exception{
		if ( facade.searchProject("a"))
			facade.removeProject("a");
		
		if ( facade.searchProject("b"))
			facade.removeProject("b");
		
		if ( facade.searchProject("c"))
			facade.removeProject("c");
		
		facade.createProject("a","junit4");
		facade.createProject("b","junit4");
		facade.createProject("c","junit4");
		
		assertTrue(facade.searchProject("c"));
		assertTrue(facade.searchProject("b"));
		assertTrue(facade.searchProject("a"));
		
		facade.removeProject("a");
		facade.removeProject("b");
		facade.removeProject("c");
		
		assertFalse(facade.searchProject("c"));
		assertFalse(facade.searchProject("b"));
		assertFalse(facade.searchProject("a"));
		
	}
	
	
	@Test
	public void testFacadeListAllProjects() throws Exception{
		if ( facade.searchProject("a"))
			facade.removeProject("a");
		
		facade.createProject("a","junit4");
		
		List<PriorJProject> projectList = facade.getProjects();
		
		assertFalse(projectList.isEmpty());
	}
	
	@Test
	public void testFacadeRunRBARenameMethod(){
		
		String pathApp = PathTo.APP_CODE;
		String className = "calc.Calculator";
		String methodName = "div";
		String newMethodName= "division";
		
		List<String> impactedMethods = facade.runRBARenameMethod(pathApp, className, methodName, newMethodName);
		
		assertFalse(impactedMethods.isEmpty());
	}
	
	@Test
	public void testFacadeRunRBAExtractMethod(){
		
		//error here
	}
	
	@Test
	public void testFacadeRunRBAMoveMethod(){
		
		String pathApp = PathTo.APP_CODE;
		
		String classOneName="model.Person";
		String classTwoName="model.Employee";
		String methodName = "toString()";
		
		List<String> impactedMethods = facade.runRBAMoveMethod(pathApp, classOneName, classTwoName, methodName);
		System.out.println(impactedMethods);
		assertFalse(impactedMethods.isEmpty());
	}
	
	@Test
	public void testFacadeRunRBAPullUpMethodMethod(){
		
		String pathApp = PathTo.APP_CODE;
		String className = "calc.Calculator";
		String methodName = "div";
		String newMethodName= "division";
		
		List<String> impactedMethods = facade.runRBARenameMethod(pathApp, className, methodName, newMethodName);
		
		assertFalse(impactedMethods.isEmpty());
	}
	
	
	@Test
	public void testFacadeRunRBAPullUpFieldMethod(){
		
		String pathApp = PathTo.APP_CODE;
		String className = "calc.Calculator";
		String methodName = "div";
		String newMethodName= "division";
		
		List<String> impactedMethods = facade.runRBARenameMethod(pathApp, className, methodName, newMethodName);
		
		assertFalse(impactedMethods.isEmpty());
	}
	
	@Test
	public void testFacadeRunRBAAddParameter(){
		
		String pathApp = PathTo.APP_CODE;
		String className = "calc.Calculator";
		String methodName = "div";
		String newMethodName= "division";
		
		List<String> impactedMethods = facade.runRBARenameMethod(pathApp, className, methodName, newMethodName);
		
		assertFalse(impactedMethods.isEmpty());
	}
	
	
	
}
