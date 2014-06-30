package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import integration.IntegrationResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.EmptySetOfTestCaseException;
import technique.TechniqueCreator;

import com.java.io.JavaIO;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
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
	private String localbase="";
	private String slash = "";
	
	
	@Before
	public void setUp(){
		priorj = PriorJ.getInstance();
		slash = JavaIO.SEPARATOR;
		localbase = JavaIO.USER_DIR + slash + "workspace" + slash;
		IntegrationResource.allSuites.add(new ArrayList<TestSuite>());
		IntegrationResource.allSuites.add(new ArrayList<TestSuite>());
		IntegrationResource.allSuites.add(new ArrayList<TestSuite>());
	}
	
	@After
	public void tearDown(){
		priorj = null;
		IntegrationResource.allSuites.clear();
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
	
	
	

	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListOfTestSuite(){
		IntegrationResource.allSuites.get(0).add(new TestSuite("org", "SuiteA"));
		IntegrationResource.allSuites.get(1).add(new TestSuite("org", "SuiteB"));
		List<TestSuite> suites = priorj.getTestSuites(IntegrationResource.allSuites);
		assertTrue(suites.size()==2);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void shouldGetListOfAllTestCases(){
		TestSuite suite1 = new TestSuite("org", "SuiteA");
		suite1.addTestCase(new TestCase("testA"));
		TestSuite suite2 = new TestSuite("org", "SuiteB");
		suite2.addTestCase(new TestCase("testB"));
		suite2.addTestCase(new TestCase("testC"));
		IntegrationResource.allSuites.get(0).add(suite1);
		IntegrationResource.allSuites.get(1).add(suite2);
		List<TestSuite> suites = priorj.getTestSuites(IntegrationResource.allSuites);
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
		IntegrationResource.allSuites.get(0).add(suite1);
		IntegrationResource.allSuites.get(1).add(suite2);
		List<TestSuite> suites = priorj.getTestSuites(IntegrationResource.allSuites);
		List<TestCase> allTests = priorj.getTestCases(suites);
		List<String> result = priorj.prioritize(TechniqueCreator.RANDOM, allTests);
		
		assertTrue(!result.isEmpty());
	}
			
//	@Test
//	public void shouldCreateOrderReport() throws Exception {
//		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
//		String report = priorj.createOrderReport(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE, results);
//		assertTrue(report.contains("1 - testY"));
//		assertTrue(report.contains("2 - testD"));
//		assertTrue(report.contains("3 - testB"));
//		assertTrue(report.contains("4 - testX"));
//	}
	
	@Test   
	public void shouldCreateSuite() throws Exception {
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String suiteName = TechniqueCreator.acronyms(TechniqueCreator.CHANGED_BLOCKS);
		String suitecode = priorj.createSuite(suiteName, results);
		assertTrue(suitecode.contains("class CB"));
	}
	
	
	@Test
	public void shouldPrioritizeWithManyTechniques() throws Exception{
		if (priorj.getTechniques().size()>0){
			priorj.getTechniques().clear();
		}
		IntegrationResource.initSampleSuiteList();
		List<TestSuite> suites = priorj.getTestSuites(IntegrationResource.allSuites);
		List<TestCase> allTests = priorj.getTestCases(suites);
		
		DataManager.createLocalbase(localbase);
		DataManager.createFolderVersion("techniquesAll", "priorOne");
		
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		priorj.addTechnique(TechniqueCreator.RANDOM);
		priorj.addTechnique(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		priorj.addTechnique(TechniqueCreator.TOTAL_STATEMENT_COVERAGE); 
		
		priorj.addTechnique(TechniqueCreator.CHANGED_BLOCKS);
		priorj.setAffectedBlocks(Arrays.asList("com.SuiteA.testA"));
		
		priorj.prioritizeAll(allTests);
		
		List<String> filesJs = new ArrayList<String>();
		List<String> filesJv = new ArrayList<String>();
		
		filesJv.add("AMC.java");
		filesJs.add("AMC.js");
		filesJv.add("ASC.java");
		filesJs.add("ASC.js");
		filesJv.add("TMC.java");
		filesJs.add("TMC.js");
		filesJv.add("TSC.java");
		filesJs.add("TSC.js");
		filesJv.add("RND.java");
		filesJs.add("RND.js");
		filesJv.add("CB.java");
		filesJs.add("CB.js");
		
		for (String filename : filesJs){
			assertTrue(JavaIO.exist(localbase+"techniquesAll"+slash+"priorOne"+slash+"js" +slash+filename));
		}
		for (String filename : filesJv){
			assertTrue(JavaIO.exist(localbase+"techniquesAll"+slash+"priorOne" +slash+filename));
		}
	}

	@Test
	public void shouldCreateCoverageReport(){
		IntegrationResource.initSampleSuiteList();
		List<TestSuite> suites = priorj.getTestSuites(IntegrationResource.allSuites);
		String coverageReport = priorj.createCoverageReport(suites);
		assertTrue(!coverageReport.isEmpty());
	}
	
	@Test 
	public void shouldInstrumentationCodeInPathLocation() throws Exception{
		String code = "public class C1 {\n";
		code +="\tpublic void m(int x){\n";
	    code += "\t\tif(x>1){\n";
		code += "\t\t\tx= x+1;\n";
		code += "\t\t}\n";
		code += "\t}\n";
		code += "}\n";
		
		DataManager.createLocalbase("c:/tests");
		DataManager.createFolderVersion("instrument", "java1");
		DataManager.save("C1.java",code);
		
		priorj.instrument("c:/tests/instrument/java1/");
		String codeOpened = DataManager.openFile("c:/tests/instrument/java1/C1.java");
		assertTrue(!code.equals(codeOpened));
		assertTrue(codeOpened.contains("watchPriorJApp = watchPriorJApp"));
	}
	
	@Test
	public void shouldCheckDifferencesApp() throws Exception {
		DataManager.createLocalbase("c:/tests");
				
		String code = "package pkg;";
		code += "public class C2 {\n";
		code +="\tpublic C2(){\n";
		code +="\tint x=0;\n";
		code += "\t}\n";
		code +="\tpublic void m(int x){\n";
	    code += "\t\tif(x>1){\n";
		code += "\t\t\tx= x+1;\n";
		code += "\t\t}\n";
		code += "\t}\n";
		code += "}\n";
		
		DataManager.createFolderVersion("diffs1", "pkg");
		DataManager.save("C2.java",code);
		
		priorj.instrument("c:/tests/diffs1/");
		
		code = "package pkg;";
		code += "public class C2 {\n";
		code +="\tpublic C2(){\n";
		code +="\tint x = 0;\n";
		code += "\t}\n";
		code +="\tpublic void m(int x){\n";
	    code += "\t\tif(x>1){\n";
		code += "\t\t\tx= x+2;\n"; //modification here
		code += "\t\tint copy =x;\n";
		code += "\t\t}\n";
		code += "\t}\n";
		code += "}\n";
		
		DataManager.createFolderVersion("diffs2", "pkg");
		DataManager.save("C2.java",code);
		priorj.instrument("c:/tests/diffs2/");
		
		List<String> diff = priorj.checkDifference("c:/tests/diffs1/pkg/C2.java", "c:/tests/diffs2/pkg/C2.java");
		//System.out.println(diff);
		assertTrue(diff.size()==2);
	}
	
	@Test
	public void shouldSelectionSuiteFractions(){
		List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
		List<String> selection =priorj.getSelection(50, list);
		assertTrue(selection.size()==3);
	}
	
}