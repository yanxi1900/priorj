package main;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;
import exception.InstrumentationUnrealizedException;
import main.PriorJFacade;
import util.Settings;


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
	private final String pathNotFound = "xpto" + Settings.SEPARATOR + "project";
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
	
	@Test (expected = IllegalArgumentException.class)
	public void testPathCodeEmpty(){
		facade.setPathCode(pathEmpty);
	}
	
	@Test  (expected = IllegalArgumentException.class )
	public void testPathCodeNotFound(){
		facade.setPathCode(pathNotFound);
	}
	
	@Test  // this case is permitted, project don't have libraries
	public void testPathLibIsEmpty(){
		facade.setPathLib("");
		assertEquals("", facade.getPathLib());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPathLibNonEmptyNotFound(){
		facade.setPathLib("lib");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPathTestEmpty(){
		facade.setPathTest(pathEmpty);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPathTestDirectoryNotFound(){
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
	
	//@Test 
	public void testTryRunWithoutPaths() throws InstrumentationUnrealizedException{
		facade.runInstrumentation();
	}
	
	//@Test (expected = InstrumentationUnrealizedException.class)
	public void testTryRunWithoutJUnitVersion() throws InstrumentationUnrealizedException{
		facade.runInstrumentation();
	}
	
}
