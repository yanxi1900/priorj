package controller;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.PriorJController;

public class PriorJControllerTest {
	
	private PriorJController controller;
	private final String separator = System.getProperty("file.separator");
	
	@Before
	public void setUp(){
		controller = new PriorJController();
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
		assertEquals(false, controller.hasPaths());
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
	
}
