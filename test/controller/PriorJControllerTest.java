package controller;
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
import org.junit.Before;
import org.junit.Test;

import project.JUnitVersionEnum;

import util.PathTo;

import controller.PriorJController;
import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;

public class PriorJControllerTest {
	
	private PriorJController controller;
	private final String separator = System.getProperty("file.separator");
	
	@Before
	public void setUp(){
		controller = new PriorJController();
		
		controller.setPathApp(PathTo.APP);
		controller.setPathCode(PathTo.APP_CODE);
		controller.setPathLib(PathTo.APP_LIB);
		controller.setPathTests(PathTo.APP_TEST);
		controller.setJUnitVersion(JUnitVersionEnum.JUNIT4);
	}
	
	@After
	public void tearDown(){
		controller = null;
	}
	
	@Test
	public void testInitialization(){
		assertEquals(false, controller.isChanged());
		assertEquals(false, controller.isInstrumented());
		assertEquals(false, controller.isCovered());
		assertEquals(false, controller.isReadLog());
		assertEquals(false, controller.isPrioritized());
		assertEquals(true, controller.hasPaths());
		assertEquals(false, controller.isNull());
	}

	@Test
	public void testPathApp(){
		controller.setPathApp("myproject");
		assertEquals("myproject", controller.getPathApp());
	}
	
	@Test
	public void testPathCode(){
		controller.setPathCode("myproject" + separator + "src");
		assertEquals("myproject" + separator + "src", controller.getPathCode());
	}
	
	@Test
	public void testPathLibEmpty(){
		controller.setPathLib("");
		assertEquals("", controller.getPathLib());
	}
	
	@Test
	public void testPathLibNonEmpty(){
		controller.setPathLib("lib");
		assertEquals("lib", controller.getPathLib());
	}
	
	@Test
	public void testPathTest(){
		controller.setPathTests("myproject" + separator +"tests");
		assertEquals("myproject" + separator +"tests", controller.getPathTests());
	}
	
	@Test
	public void testPathNewCode(){
		controller.setPathNew("mynewproject" + separator + "src");
		assertEquals("mynewproject" + separator + "src", controller.getPathNew());
	}
	
	@Test
	public void testHasPathIsTrue(){
		controller.setPathApp("myproeject");
		controller.setPathCode("myproeject" + separator + "src");
		controller.setPathLib("");
		controller.setPathTests("myproject" + separator + "tests");
		
		assertTrue(controller.hasPaths());
	}
	
	@Test
	public void testAddTechniques() throws Exception{
		controller.addTechnique("TMC");
		controller.addTechnique("TSC");
		controller.addTechnique("AMC");
		controller.addTechnique("ASC");
		controller.addTechnique("RBA");
		
		assertEquals(5, controller.getNumberOfTechniques());
	}
	
	@Test
	public void testRemoveTechniques() throws Exception{
		controller.addTechnique("TMC");
		controller.addTechnique("TSC");
		controller.addTechnique("AMC");
		controller.addTechnique("ASC");
		controller.addTechnique("RBA");
		
		controller.removeTechnique("RBA");
		controller.removeTechnique("TMC");
		
		assertEquals(3, controller.getNumberOfTechniques());
	}
	
	
	@Test
	public void testControllerRunInstrumentation() throws InstrumentationUnrealizedException{
		
		controller.runInstrumentation();
		
		assertTrue(controller.isInstrumented());
	}
	
	@Test 
	public void testControllerRunCoverage() throws InstrumentationUnrealizedException, CoverageUnrealizedException{
				
		controller.runInstrumentation();
		controller.runCoverage();
		
		assertTrue(controller.isCovered());
	}
	
	@Test
	public void testControllerRunReadLog() throws InstrumentationUnrealizedException, CoverageUnrealizedException, CannotReadLogFileException{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
	
		assertTrue(controller.isReadLog());
	}
	
	@Test
	public void testControllerAddTechniqueAndRunPrioritization() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		controller.addTechnique("TMC");
		
		controller.runPrioritization();
		
		assertTrue(controller.isPrioritized());		
	}
	

	@Test
	public void testControllerRunPrioritizationTMC() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		List<String> tests = controller.runPrioritizationTMC();
		
		assertTrue(controller.isPrioritized());	
		assertFalse(tests.isEmpty());
	}
	
	
	@Test
	public void testControllerRunPrioritizationTSC() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		List<String> tests = controller.runPrioritizationTSC();
		
		assertTrue(controller.isPrioritized());	
		assertFalse(tests.isEmpty());
	}
	
	@Test
	public void testControllerRunPrioritizationAMC() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		List<String> tests = controller.runPrioritizationAMC();
		
		assertTrue(controller.isPrioritized());	
		assertFalse(tests.isEmpty());
	}
	
	@Test
	public void testControllerRunPrioritizationASC() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		List<String> tests = controller.runPrioritizationASC();
		
		assertTrue(controller.isPrioritized());	
		assertFalse(tests.isEmpty());
	}
	
	@Test
	public void testControllerRunPrioritizationRND() throws Exception{
		
		controller.runInstrumentation();
		controller.runCoverage();
		controller.runReadLog();
		
		List<String> tests = controller.runPrioritizationRND();
		
		assertTrue(controller.isPrioritized());	
		assertFalse(tests.isEmpty());
	}
	
	@Test
	public void testPrioritizedTestCasePosition(){
		
	}
	
	//tests changed blocks and RBA
	
	@Test
	public void testControllerRunPrioritizationCB() throws Exception{
		//not implemented
	}
	
	@Test
	public void testControllerRunPrioritizationRBA() throws Exception{
		//not implemented
	}
	
	
}
