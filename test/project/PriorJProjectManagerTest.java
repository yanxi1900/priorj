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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;


public class PriorJProjectManagerTest {

	private PriorJProjectManager manager;

	@Before
	public void setUp(){
		manager = new PriorJProjectManager();
	}
	
	@After
	public void tearDown(){
		manager = null;
	}
	
	@Test
	public void testPriorJProjectManagerInitialization() throws Exception{
		assertNotNull(manager);
	}
	
	@Test (expected =  EmptyPriorJProjectNameException.class)
	public void testCreateProjectWithEmptyName() throws EmptyPriorJProjectNameException, DuplicateProjectNameException{
		manager.createNewProject("", JUnitVersionEnum.JUNIT3);
	}
	
	@Test
	public void testCreatePriorJProject() throws EmptyPriorJProjectNameException, DuplicateProjectNameException{
		
		if(manager.searchProject("test"))
			manager.deleteProject("test");
		
		manager.createNewProject("test", JUnitVersionEnum.JUNIT3);
	
		assertTrue(manager.searchProject("test"));
	}
	
	@Test (expected = DuplicateProjectNameException.class)
	public void testCreateDuplicatedProject() throws EmptyPriorJProjectNameException, DuplicateProjectNameException{
	
		if(manager.searchProject("test"))
			manager.deleteProject("test");
		
		manager.createNewProject("test", JUnitVersionEnum.JUNIT3);
		manager.createNewProject("test", JUnitVersionEnum.JUNIT3);
		
		assertTrue(manager.searchProject("test"));
	}
	
	@Test
	public void testSearchProject() throws EmptyPriorJProjectNameException, DuplicateProjectNameException{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		if(manager.searchProject("b"))
			manager.deleteProject("b");
		
		if(manager.searchProject("c"))
			manager.deleteProject("c");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		manager.createNewProject("b", JUnitVersionEnum.JUNIT3);
		manager.createNewProject("c", JUnitVersionEnum.JUNIT3);
		
		assertTrue(manager.searchProject("a"));
		assertTrue(manager.searchProject("b"));
		assertTrue(manager.searchProject("c"));
		assertFalse(manager.searchProject("x"));
		
	}
	
	@Test
	public void testRemoveProject() throws EmptyPriorJProjectNameException, DuplicateProjectNameException{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		if(manager.searchProject("b"))
			manager.deleteProject("b");
		
		if(manager.searchProject("c"))
			manager.deleteProject("c");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		manager.createNewProject("b", JUnitVersionEnum.JUNIT3);
		manager.createNewProject("c", JUnitVersionEnum.JUNIT3);

		manager.deleteProject("a");
		manager.deleteProject("b");
		
		assertFalse(manager.searchProject("a"));
		assertFalse(manager.searchProject("b"));
		assertTrue(manager.searchProject("c"));

	}
		
	@Test
	public void testProjectOpened() throws Exception{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		
		assertTrue(manager.hasOpenProject());
		
	}
	
	@Test
	public void testProjectClosed() throws Exception{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		
		manager.closeProject();
		
		assertFalse(manager.hasOpenProject());
	}
	
	
	@Test
	public void testHasPathsFalse() throws Exception{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		
		assertEquals("", manager.getPathApp());
		assertEquals("", manager.getPathCode());
		assertEquals("", manager.getPathLib());
		assertEquals("", manager.getPathTest());
		assertEquals("", manager.getPathNewCode());	
	}
	
	@Test
	public void testSetAllPaths() throws Exception{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
		
		manager.setPaths("app", "code", "lib", "test", "codenew"); 
				
		assertEquals("app", manager.getPathApp());
		assertEquals("code", manager.getPathCode());
		assertEquals("lib", manager.getPathLib());
		assertEquals("test", manager.getPathTest());
		assertEquals("codenew", manager.getPathNewCode());	
	}
	
	@Test
	public void testAccessOpenProject() throws Exception{
		if(manager.searchProject("a"))
			manager.deleteProject("a");
		
		manager.createNewProject("a", JUnitVersionEnum.JUNIT3);
	
		PriorJProject project = manager.getOpenProject();
		
		assertEquals("a", project.getName());
		
	}
	
	
	
	
}
