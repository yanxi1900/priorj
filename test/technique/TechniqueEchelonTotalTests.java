package technique;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import exception.EmptySetOfTestCaseException;

import util.PathTo;
import input.InputParse;
import coverage.Statement;
import coverage.ClassCode;
import coverage.Method;

public class TechniqueEchelonTotalTests {

	private String path;
	
	private String filename;
	private String filemodified;
	
	private InputParse parse;
	
	private TechniqueEchelonTotal etc;
	
	@Before
	public void setUp() {
		filename = "testsuite.txt";
		filemodified = "testsuiteMod(2).txt";
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR;
	}
	
	@After
	public void tearDown(){
		filename = "";
		filemodified = "";
		path = "";
	}
	
	
	@Test
	public void testEchelonAdditionalAffectedBlock() {
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
		
		List<TestCase> tests = new ArrayList<TestCase>();
		List<String> list =  Arrays.asList("a", "b", "c","d");
		
		etc = new TechniqueEchelonTotal(list, tests);
		
		assertEquals(list.toString(), etc.getBlockAffected().toString());
	}
	
	@Test
	public void testContainBlockTrue() {
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
	
		List<TestCase> tests = parse.getResultAsTestCase();
		
		List<String> list = new ArrayList<String>();
		
		list.add("pkg3.classD.methodA37.23");
		list.add("pkg3.classD.methodA37.97");
		list.add("pkg3.classD.methodA37.100"); 
						
		etc = new TechniqueEchelonTotal(list, tests);
		
		assertTrue(etc.containsBlock("pkg3.classD.methodA37.23"));
		assertTrue(etc.containsBlock("pkg3.classD.methodA37.97"));
		assertTrue(etc.containsBlock("pkg3.classD.methodA37.100"));	
	}
	
	@Test
	public void testContainBlockFalse() {
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
	
		List<TestCase> tests = parse.getResultAsTestCase();
		
		List<String> list = new ArrayList<String>();
		
		list.add("pkg3.classD.methodA37.23");
		list.add("pkg3.classD.methodA37.97");
		list.add("pkg3.classD.methodA37.100");
						
		etc = new TechniqueEchelonTotal(list, tests);
		
		assertFalse(etc.containsBlock("pkgA.classB.methodA.51"));
		assertFalse(etc.containsBlock("pkgA.classB.methodA.52"));
		assertFalse(etc.containsBlock("pkgA.classB.methodA.33"));
		
	}
	
	@Test
	public void testAssignWeightForTest() throws EmptySetOfTestCaseException{
		//new version
		parse = new InputParse(path  + filemodified, "testsuite");
		parse.runParse();
	
		
		List<TestCase> tests = parse.getResultAsTestCase();
		List<String> listModified = new ArrayList<String>();
		
		listModified.add("pkgD.classD.methodA37.100");
		listModified.add("pkgD.classD.methodA37.97");
		listModified.add("pkgD.classD.methodA37.23");
		
		//old version
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
		
		etc = new TechniqueEchelonTotal(listModified, tests);
		
		List<String> prior = new ArrayList<String>();
		
		String first = "pkg3.SuiteC.testD";
		
		for (int i=0; i<=3; i++){
			prior = etc.assingWeight();
//			System.out.println(prior);
			assertEquals(first, prior.get(0));
		}
		
//		for (TestCase test : tests) {
//			
//			for (ClassCode classcode : test.getClassCoverage()) {
//				for ( Method method : classcode.getMethodCoverage()){
//					for (Statement stmt : method.getUniqueStatements()){
//						System.out.println(classcode.toString() + "."+ method.getName() + "." + stmt.getLineNumber());
//					}
//				}
//				
//			}
//		}
	}
	
	@Test
	public void techniqueEchelonTotalOCM() throws EmptySetOfTestCaseException {
		//Old version
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
		
		//New vresion
		parse = new InputParse(path + filemodified, "testsuite");
		parse.runParse();
		
		List<TestCase> tests = parse.getResultAsTestCase();
		List<String> listModified = new ArrayList<String>();
		
		listModified.add("pkgD.classD.methodA37.23");
		listModified.add("pkgD.classD.methodA37.97");
		listModified.add("pkgD.classD.methodA37.100");
		
		etc = new TechniqueEchelonTotal(listModified, tests);
		assertTrue(etc.containsBlock("pkgD.classD.methodA37.23"));
		assertTrue(etc.containsBlock("pkgD.classD.methodA37.97"));
		assertTrue(etc.containsBlock("pkgD.classD.methodA37.100"));	
		
		assertTrue(etc.assingWeight().get(0).equals("pkg3.SuiteC.testD"));
		assertTrue(etc.assingWeight().contains("pkg3.SuiteC.testC"));
		assertTrue(etc.assingWeight().contains("pkg2.SuiteB.testB"));
		assertTrue(etc.assingWeight().contains("pkg1.SuiteA.testA"));
		
		
	}
}
