package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.TechniqueCreator;

import com.java.io.JavaIO;

import coverage.TestSuite;

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

	@Test
	public void shouldAllowTheUserRemoveTechniques(){
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		priorj.addTechnique(TechniqueCreator.CHANGED_BLOCKS);
		priorj.addTechnique(TechniqueCreator.RANDOM);
		priorj.addTechnique(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		
		assertTrue(priorj.getTechniques().size()==6);
		
		priorj.removeTechnique(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		priorj.removeTechnique(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		priorj.removeTechnique(TechniqueCreator.CHANGED_BLOCKS);
		priorj.removeTechnique(TechniqueCreator.RANDOM);
		priorj.removeTechnique(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		
		assertTrue(priorj.getTechniques().get(0)==TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		
		priorj.removeTechnique(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		
		assertTrue(priorj.getTechniques().size()==0);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void shouldSaveCoverageDataAnyWhere(){
		List<List> allSuites = new ArrayList<List>();
		allSuites.add(new ArrayList<TestSuite>());
		allSuites.add(new ArrayList<TestSuite>());
		allSuites.add(new ArrayList<TestSuite>());
		priorj.saveCoverageData("c:/tests/tdd/","coverage.xml", allSuites);
		assertTrue(JavaIO.exist("c:/tests/tdd/coverage.xml"));
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void shouldOpenCoverageFile(){
		List<List> allSuites = new ArrayList<List>();
		allSuites.add(new ArrayList<TestSuite>());
		allSuites.add(new ArrayList<TestSuite>());
		priorj.saveCoverageData("c:/tests/tdd/open/","coverage.xml", allSuites);
		
		List<List> coverage = priorj.openCoverageData("c:/tests/tdd/open/coverage.xml");
		assertTrue(coverage.size() == 2);
	}
}
