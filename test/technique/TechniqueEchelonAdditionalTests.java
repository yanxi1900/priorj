package technique;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;

import util.PathTo;
import input.InputParse;

public class TechniqueEchelonAdditionalTests {
	
	private String path;
	
	private String filename;
	private String filemodified;
	
	private InputParse parse;
	
	private TechniqueEchelonAdditional eac;

	@Before
	public void setUp() {
		filename = "testsuite.txt";
		filemodified = "testsuiteMod.txt";
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
		
		eac = new TechniqueEchelonAdditional(list, tests);
		
		assertEquals(list.toString(), eac.getBlockAffected().toString());
	}
	
	
	@Test
	public void testContainBlockTrue() {
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
	
		List<TestCase> tests = parse.getResultAsTestCase();
		
		List<String> list = new ArrayList<String>();
		
		list.add("pkgA.classA.methodA.51");
		list.add("pkgA.classA.methodA.52");
		list.add("pkgA.classA.methodA.33");
		list.add("pkgA.classA.methodA.42"); 
						
		eac = new TechniqueEchelonAdditional(list, tests);
		
		assertTrue(eac.containsBlock("pkgA.classA.methodA.51"));
		assertTrue(eac.containsBlock("pkgA.classA.methodA.52"));
		assertTrue(eac.containsBlock("pkgA.classA.methodA.33"));
		assertTrue(eac.containsBlock("pkgA.classA.methodA.42"));	
	}
	
	@Test
	public void testContainBlockFalse() {
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
	
		List<TestCase> tests = parse.getResultAsTestCase();
		
		List<String> list = new ArrayList<String>();
		
		list.add("pkgA.classA.methodA.51");
		list.add("pkgA.classA.methodA.52");
		list.add("pkgA.classA.methodA.33");
		list.add("pkgA.classA.methodA.42"); 
						
		eac = new TechniqueEchelonAdditional(list, tests);
		
		assertFalse(eac.containsBlock("pkgA.classB.methodA.51"));
		assertFalse(eac.containsBlock("pkgA.classB.methodA.52"));
		assertFalse(eac.containsBlock("pkgA.classB.methodA.33"));
		assertFalse(eac.containsBlock("pkgA.classB.methodA.42"));
		
	}
	
	@Test
	public void testAssignWeightForTest(){
		//new version
		parse = new InputParse(path  + filemodified, "testsuite");
		parse.runParse();
	
		
		List<TestCase> tests = parse.getResultAsTestCase();
		List<String> listModified = new ArrayList<String>();
		listModified.add("pkgA.classA.methodB.91");
		listModified.add("pkgA.classA.methodB.22");
		listModified.add("pkgA.classA.methodB.23");
		listModified.add("pkgA.classA.methodB.98");
		
		//old version
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
		// no modifications in old version	
		eac = new TechniqueEchelonAdditional(listModified, tests);
		
		List<String> prior = new ArrayList<String>();
		
		String first = "pkg1.SuiteA.testA";
		
		for (int i=0; i<=3; i++){
			prior = eac.assingWeight();
			assertEquals(first, prior.get(0));
		}
	}
	
	@Test
	public void techniqueEchelonAdditionalOCM() {
		//Old version
		parse = new InputParse(path + filename, "testsuite");
		parse.runParse();
		
		//New vresion
		parse = new InputParse(path + filemodified, "testsuite");
		parse.runParse();
		
		List<TestCase> tests = parse.getResultAsTestCase();
		List<String> listModified = new ArrayList<String>();
		
		listModified.add("pkgA.classA.methodB.91");
		listModified.add("pkgA.classA.methodB.22");
		listModified.add("pkgA.classA.methodB.23");
		listModified.add("pkgA.classA.methodB.98");
		
		//Four mofications (One Class Modified)
		eac = new TechniqueEchelonAdditional(listModified, tests);
		
		assertTrue(eac.containsBlock("pkgA.classA.methodB.91"));
		assertTrue(eac.containsBlock("pkgA.classA.methodB.22"));
		assertTrue(eac.containsBlock("pkgA.classA.methodB.23"));
		assertTrue(eac.containsBlock("pkgA.classA.methodB.98"));	
		
		assertTrue(eac.assingWeight().get(0).equals("pkg1.SuiteA.testA"));
		assertTrue(eac.assingWeight().get(1).equals("pkg2.SuiteB.testB"));
		assertTrue(eac.assingWeight().get(2).equals("pkg3.SuiteC.testC"));
		assertTrue(eac.assingWeight().get(3).equals("pkg3.SuiteC.testD"));
		
//		System.out.println(eac.assingWeight());
	}
	
	
}
