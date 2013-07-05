package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
	
	
}
