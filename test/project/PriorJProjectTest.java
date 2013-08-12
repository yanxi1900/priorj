package project;

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

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import exception.EmptyPriorJProjectNameException;

/**
 * This class is a test for class PriorJProject.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJProjectTest {

	private PriorJProject project;
	private final String separator = System.getProperty("user.dir");
	
	@Before
	public void setUp(){
		project = new PriorJProject();	
	}
	
	@Test
	public void testPriorJProjectInitialization(){
		assertEquals("", project.getName());
		assertEquals("", project.getPathApp());
		assertEquals("", project.getPathCode());
		assertEquals("", project.getPathLib());
		assertEquals("", project.getPathTest());
		assertEquals("", project.getPathCodeNew());
		assertEquals(JUnitVersionEnum.JUNIT3, project.getVersion());
		
	}
	
	@Test(expected = EmptyPriorJProjectNameException.class)
	public void testCreateProjectWithEmptyName() throws EmptyPriorJProjectNameException {
		project = new PriorJProject("", Calendar.getInstance(), JUnitVersionEnum.JUNIT3);
	}

	@Test
	public void testPriorJProjectSetName(){
		project.setName("myproject");
		assertEquals("myproject", project.getName());
	}
	
	@Test
	public void testSetPathApplication(){
		project.setPathApp("myproject" + separator + "AA");
		assertEquals("myproject" + separator + "AA", project.getPathApp());
	}
	
	@Test
	public void testSetPathCode(){
		project.setPathCode("myproject" + separator + "AA" + separator + "src");
		assertEquals("myproject" + separator + "AA" + separator + "src", project.getPathCode());
	}
	
	@Test
	public void testSetEmptyPathLib(){
		project.setPathLib("");
		assertEquals("", project.getPathLib());
	}
	
	@Test
	public void testSetNonEmptyPathLib(){
		project.setPathLib("myproject" + separator + "AA" + separator + "lib");
		assertEquals("myproject" + separator + "AA" + separator + "lib", project.getPathLib());
	}
	
	
	@Test
	public void testSetPathTest(){
		project.setPathTest("myproject" + separator + "AA" + separator + "test");
		assertEquals("myproject" + separator + "AA" + separator + "test", project.getPathTest());
	}
	
	@Test
	public void testSetPathNewCode(){
		project.setPathCodeNew("myNewProject" + separator + "AA" + separator + "src");
		assertEquals("myNewProject" + separator + "AA" + separator + "src", project.getPathCodeNew());
	}
	
	@Test
	public void testPriorJProjectIsOpen(){
		assertTrue(project.isOpenProject());
	}
	
	@Test
	public void testCloseProject(){
		project.setCloseProject();
		assertFalse(project.isOpenProject());
	}
	
	@Test
	public void  testSetAllPaths(){
		project.setPaths("app", "code", "lib", "test", "newcode");
		
		assertEquals("app", project.getPathApp());
		assertEquals("code", project.getPathCode());
		assertEquals("lib", project.getPathLib());
		assertEquals("test", project.getPathTest());
		assertEquals("newcode", project.getPathCodeNew());
	}
	
	
	@Test
	public void testEqualsPriorJProject() throws EmptyPriorJProjectNameException{
		PriorJProject projectA = new PriorJProject("a",Calendar.getInstance(),JUnitVersionEnum.JUNIT3);
		
		PriorJProject projectB = new PriorJProject("b",Calendar.getInstance(),JUnitVersionEnum.JUNIT3);
		
		assertFalse(projectA.equals(projectB));
	}
	
	
}
