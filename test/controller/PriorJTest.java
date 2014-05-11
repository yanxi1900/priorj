package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the PriorJ Class.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class PriorJTest {
	
	private PriorJ priorj;
	
	@Before
	public void setUp(){
		priorj = new PriorJ();
	}
	
	@After
	public void tearDown(){
		priorj = null;
	}
	
	@Test
	public void shouldAllowSetLocalBasePath(){
		priorj.setLocalBasePath("c:/file");
		assertEquals("c:/file", priorj.getLocalBasePath());
	}

}
