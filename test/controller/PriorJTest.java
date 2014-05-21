package controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@SuppressWarnings("rawtypes")
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
		priorj.createLocalbase("c:/file");
		assertEquals("c:/file", priorj.getLocalBasePath());
	}

	@Test
	public void shouldCreateFolderInLocalBase() throws Exception{
		priorj.createLocalbase("c:/tests/coverage/");
		assertTrue(JavaIO.exist("c:/tests/coverage/"));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithEmptyPath() throws Exception{
		priorj.createLocalbase("");
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithValueNull() throws Exception{
		priorj.createLocalbase(null);
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
		assertEquals("[testA, testB, testC]", allTests.toString());
	}
	
	@SuppressWarnings("unchecked")
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
		List<String> result = priorj.prioritize(TechniqueCreator.RANDOM, allTests);
		
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void shouldAllowTheUserGetCurrentProjectName() throws Exception{
		priorj.createProjectFolder("project1");
		assertEquals("project1", priorj.getProjectFolderName());		
	}
	
	@Test
	public void shouldCreateFolderProjectInLocalBase() throws Exception{
		priorj.createLocalbase("c:/tests/tdd/");
		priorj.createProjectFolder("project1");
		assertTrue(JavaIO.exist("c:/tests/tdd/project1"));
	}
	
//	@Test (expected = Exception.class)
//	public void shouldThrowExceptionWhenTryCreateProjectFolderWithoutLocalbase() throws Exception{
//		priorj.createProjectFolder("my_project_yvi");
//		assertTrue(priorj.getLocalBasePath().isEmpty());
//	}
	
	
	@Test
	public void shouldCreateProjectSubVersion() throws Exception{
		priorj.createLocalbase("c:/tests/");
		priorj.createFolderVersion("my_project", "my_version");
		assertTrue(JavaIO.exist("c:/tests/my_project/my_version"));
	}
	
	
	@Test
	public void shouldCreateOrderReport() throws Exception {
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String report = priorj.createOrderReport(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE, results);
		assertTrue(report.contains("1 - testY"));
		assertTrue(report.contains("2 - testD"));
		assertTrue(report.contains("3 - testB"));
		assertTrue(report.contains("4 - testX"));
	}
	
	@Test   
	public void shouldCreateSuite() throws Exception {
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String suiteName = TechniqueCreator.acronyms(TechniqueCreator.CHANGED_BLOCKS);
		String suitecode = priorj.createSuite(suiteName, results);
		assertTrue(suitecode.contains("class CB"));
	}
	
	@Test
	public void shouldSavePrioritizationOrder() throws Exception{
		priorj.createLocalbase("c:/tests");
		priorj.createFolderVersion("Open-half-One", "priorVersion1");
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String report = priorj.createOrderReport(TechniqueCreator.RANDOM, results);
		priorj.save("RND.txt", report);	
		assertTrue(JavaIO.exist("c:/tests/Open-half-One/priorVersion1/RND.txt"));
	}
	

	@Test
	public void shouldSaveSuites() throws Exception{
		priorj.createLocalbase("c:/tests");
		priorj.createFolderVersion("Open-half-One", "priorVersion1");
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String nome = TechniqueCreator.acronyms(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		System.out.println(nome);
		String suitecode = priorj.createSuite(nome, results);
		priorj.save(nome+".java",suitecode);	
		assertTrue(JavaIO.exist("c:/tests/Open-half-One/priorVersion1/"+nome+".java"));
	}
	
	@Test
	public void shouldPrioritizeWithManyTechniques() throws Exception{
		TestSuite suite1 = new TestSuite("org", "SuiteA");
		
		TestCase tcA = new TestCase("com.SuiteA.testA");
		tcA.setSignature("com.suiteA.testA");
		suite1.addTestCase(tcA);
		
		TestSuite suite2 = new TestSuite("org", "suiteB");
		
		TestCase tcB = new TestCase("com.suiteB.testB");
		tcB.setSignature("com.suiteB.testB");
		suite2.addTestCase(tcB);
		
		TestCase tcC = new TestCase("com.suiteB.testC");
		tcC.setSignature("com.suiteC.testC");
		suite2.addTestCase(tcC);
		
		allSuites.get(0).add(suite1);
		allSuites.get(1).add(suite2);
		List<TestSuite> suites = priorj.getTestSuites(allSuites);
		List<TestCase> allTests = priorj.getTestCases(suites);
		
		priorj.createLocalbase("c:/tests");
		priorj.createFolderVersion("techniquesAll", "priorOne");
		
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		priorj.addTechnique(TechniqueCreator.RANDOM);
		priorj.addTechnique(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		//priorj.addTechnique(TechniqueCreator.CHANGED_BLOCKS);
		
		priorj.prioritizeAll(allTests);
		
		List<String> files = new ArrayList<String>();
		
		files.add("AMC.java");
		files.add("AMC.txt");
		files.add("ASC.java");
		files.add("ASC.txt");
		files.add("TMC.java");
		files.add("TMC.txt");
		files.add("TSC.java");
		files.add("TSC.txt");
		files.add("RND.java");
		files.add("RND.txt");
//		files.add("CB.java");
//		files.add("CB.txt");
		
		for (String filename : files){
			assertTrue(JavaIO.exist("c:/tests/techniquesAll/priorOne/"+filename));
		}
	}

	
	@Test
	public void shouldCreateCoverageReport(){
		String coverageReport = priorj.createCoverageReport();
	}
	
}
