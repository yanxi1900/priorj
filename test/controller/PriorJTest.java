package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.EmptySetOfTestCaseException;
import technique.TechniqueCreator;

import com.java.io.JavaIO;

import coverage.TestCase;
import coverage.TestSuite;

/**
 * Testing the PriorJ Class.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class PriorJTest {
	
	private PriorJ priorj;
	List<List> allSuites = new ArrayList<List>();
	
	@Before
	public void setUp(){
		priorj = PriorJ.getInstance();
		allSuites.add(new ArrayList<TestSuite>());
		allSuites.add(new ArrayList<TestSuite>());
		allSuites.add(new ArrayList<TestSuite>());
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
		priorj.saveCoverageData("c:/tests/tdd/","coverage.xml", allSuites);
		assertTrue(JavaIO.exist("c:/tests/tdd/coverage.xml"));
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void shouldOpenCoverageFile(){
		priorj.saveCoverageData("c:/tests/tdd/open/","coverage.xml", allSuites);
		List<List> coverage = priorj.openCoverageData("c:/tests/tdd/open/coverage.xml");
		assertTrue(coverage.size() == 3);
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListOfTestSuite(){
		allSuites.get(0).add(new TestSuite("org", "SuiteA"));
		allSuites.get(1).add(new TestSuite("org", "SuiteB"));
		List<TestSuite> suites = priorj.getTestSuites(allSuites);
		assertTrue(suites.size()==2);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void shouldGetListOfAllTestCases(){
		TestSuite suite1 = new TestSuite("org", "SuiteA");
		suite1.addTestCase(new TestCase("testA"));
		TestSuite suite2 = new TestSuite("org", "SuiteB");
		suite2.addTestCase(new TestCase("testB"));
		suite2.addTestCase(new TestCase("testC"));
		allSuites.get(0).add(suite1);
		allSuites.get(1).add(suite2);
		List<TestSuite> suites = priorj.getTestSuites(allSuites);
		List<TestCase> allTests = priorj.getTestCases(suites);
	}
	
	@Test
	public void shouldPrioritizeWithOnlyOneTechnique() throws EmptySetOfTestCaseException{
		TestSuite suite1 = new TestSuite("org", "SuiteA");
		suite1.addTestCase(new TestCase("testA"));
		TestSuite suite2 = new TestSuite("org", "SuiteB");
		suite2.addTestCase(new TestCase("testB"));
		suite2.addTestCase(new TestCase("testC"));
		allSuites.get(0).add(suite1);
		allSuites.get(1).add(suite2);
		List<TestSuite> suites = priorj.getTestSuites(allSuites);
		List<TestCase> allTests = priorj.getTestCases(suites);
		List<String> prioritized = priorj.prioritize(TechniqueCreator.TOTAL_METHOD_COVERAGE, allTests);
		assertTrue(!prioritized.isEmpty());
	}
}
