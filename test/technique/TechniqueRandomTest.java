package technique;

import static org.junit.Assert.*;

import java.util.List;

import input.InputParse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;
import exception.EmptySetOfTestCaseException;

import util.PathTo;

public class TechniqueRandomTest {

	private String filename;
	private String filenameJMock;
	
	private String path;
	private String path2;
	
	private InputParse parse;
	
	private TechniqueRandom rc;
	private TechniqueRandom rc2;
	private TechniqueRandom rc3;	
	
	@Before
	public void setUp() {
		filename = "test-suite-five.txt";
		filenameJMock = "SuiteConfig.txt";
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename;
		path2 = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filenameJMock;
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testRandomAllTests() throws EmptySetOfTestCaseException {
		parse = new InputParse(path, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		rc = new TechniqueRandom(tests);
		rc2 = new TechniqueRandom(tests);
		rc3 = new TechniqueRandom(tests);
		
		List<String> prioritization = rc.assingWeight();
		List<String> prioritization2 = rc2.assingWeight();
		List<String> prioritization3 = rc3.assingWeight();
		
		assertFalse(prioritization.equals(prioritization2));
		assertFalse(prioritization.equals(prioritization3));
		assertFalse(prioritization2.equals(prioritization3));
		
//		System.out.println(prioritization);
//		System.out.println();
//		System.out.println(prioritization2);
//		System.out.println();
//		System.out.println(prioritization3);
	}
	
	@Test
	public void testRandomJMock() {
		parse = new InputParse(path2, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		rc = new TechniqueRandom(tests);
		rc2 = new TechniqueRandom(tests);
		rc3 = new TechniqueRandom(tests);
		
		List<String> prioritization = rc.assingWeight();
		List<String> prioritization2 = rc2.assingWeight();
		List<String> prioritization3 = rc3.assingWeight();
		
		assertFalse(prioritization.equals(prioritization2));
		assertFalse(prioritization.equals(prioritization3));
		assertFalse(prioritization2.equals(prioritization3));
		
//		System.out.println(prioritization);
//		System.out.println();
//		System.out.println(prioritization2);
//		System.out.println();
//		System.out.println(prioritization3);
	}
	
}
