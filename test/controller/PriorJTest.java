package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.TechniqueCreator;

import com.java.io.JavaIO;

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
		priorj = PriorJ.getInstance();
	}
	
	@After
	public void tearDown(){
		priorj = null;
	}
	
	@Test
	public void shouldAllowSetLocalBasePath() throws Exception{
		priorj.setLocalBasePath("c:/file");
		assertEquals("c:/file", priorj.getLocalBasePath());
	}

	@Test
	public void shouldCreateFolderInLocalBase() throws Exception{
		priorj.setLocalBasePath("c:/tests/coverage/");
		assertTrue(JavaIO.exist("c:/tests/coverage/"));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithEmptyPath() throws Exception{
		priorj.setLocalBasePath("");
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithValueNull() throws Exception{
		priorj.setLocalBasePath(null);
	}
	
	@Test
	public void shoudAllowTheUserAddTechniques(){
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		priorj.addTechnique(TechniqueCreator.CHANGED_BLOCKS);
		priorj.addTechnique(TechniqueCreator.RANDOM);
		priorj.addTechnique(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		
		assertTrue(priorj.getTechniques().size()==6);
	}
	
}
