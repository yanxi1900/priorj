package prioritization;

import static org.junit.Assert.*;

import java.util.List;

import input.InputParse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import exception.EmptySetOfTestCaseException;

import technique.Technique;
import technique.TechniqueTotalMethod;
import technique.TechniquesEnum;
import util.PathTo;

/**
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class TMCPrioritizationTest {

	Technique tmc;
	InputParse parse;
	String path = "";
	String filename = "";
	
	@Before
	public void setUp() throws Exception {
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR;
	}

	@After
	public void tearDown() throws Exception {
		tmc = null;
	}

	@Test
	public void testPrioritizationWithDistinctsWeightsMethods() throws EmptySetOfTestCaseException {
		filename = "test-suite-tmc01.txt";
		
		parse = new InputParse(path+filename,"testsuite");
		parse.runParse();
		
		List<TestCase> tests = parse.getResultAsTestCase();
		
		tmc = new TechniqueTotalMethod(tests);
		
		List<String> prioritized = tmc.assingWeight();
		
		assertEquals("[pkg1.SuiteA.test07, pkg1.SuiteA.test06, pkg1.SuiteA.test05, pkg1.SuiteA.test04, pkg1.SuiteA.test03, pkg1.SuiteA.test02, pkg1.SuiteA.test01]",prioritized.toString());
		
	}

}
