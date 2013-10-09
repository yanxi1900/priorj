package input;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;

import util.PathTo;

public class InputParseTest {
	
	InputParse parse;
	String filename = "inputconfig.txt";
	String local = PathTo.RESOURCES_FILES;
	String separator = PathTo.SEPARATOR;
	String path = "";
	
	@Before
	public void setUp() throws Exception {
		parse = new InputParse();
		path = local + separator + filename;
	}

	@After
	public void tearDown() throws Exception {
		parse = null;
	}
	
	@Test
	public void testParseExtractData(){
		InputObject obj = parse.extractData("[rm_new_method_name]:[empty]");
		
		assertEquals("rm_new_method_name", obj.getProperty());
		assertEquals("", obj.getValue());
	}

	@Test
	public void testParseLineInvalid() {
		InputObject obj = parse.lineParse("*[_paths_config_]");
		
		assertEquals("", obj.getProperty());
		assertEquals("", obj.getValue());
	}
	
	@Test
	public void testParseLineValid() {
		InputObject obj = parse.lineParse("[rm_new_method_name]:[empty]");
		
		assertEquals("rm_new_method_name", obj.getProperty());
		assertEquals("", obj.getValue());
	}
	
	@Test
	public void testCheckFileTrue(){
		parse = new InputParse(path);
		
		assertTrue( parse.checkFile());
	}
	
	@Test
	public void testDoParse(){
		parse = new InputParse(path);
		
		parse.runParse();
		
		List<InputObject> resultList = parse.getResult();
		
		assertFalse(resultList.isEmpty());
	}
	
	@Test
	public void testDoParseForTestSuite(){
		
		filename = "test-suite-one.txt";
		
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR +filename;
		
		parse = new InputParse(path, "testsuite");
		
		parse.runParse();
		
		List<TestSuite> suites = parse.getResultAsSuite();
		
		assertTrue(suites.size() == 3);
	}
	
	@Test
	public void testDoParseForTestCase(){
		
		filename = "test-suite-two.txt";
		
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR +filename;
		
		parse = new InputParse(path, "testsuite");
		
		parse.runParse();
		
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		assertTrue(suites.size() == 3);
		assertTrue(tests.size() == 15);
		
	}
	
	@Test
	public void testDoParseForClassCode(){
		
		filename = "test-suite-three.txt";
		
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR +filename;
		
		parse = new InputParse(path, "testsuite");
		
		parse.runParse();
		
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		assertTrue(suites.size() == 3);
		
		for(TestCase test : tests)
			assertTrue(test.getClassCoverage().size() > 0);
	}

	@Test
	public void testDoParseForMethods(){
		
		filename = "test-suite-four.txt";
		
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR +filename;
		
		parse = new InputParse(path, "testsuite");
		
		parse.runParse();
		
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		assertTrue(suites.size() == 3);
		
		for(TestCase test : tests)
			for (ClassCode classcode : test.getClassCoverage()){
				for (Method method : classcode.getMethodCoverage()){
					assertTrue(classcode.getMethodCoverage().size() > 0);
				}
			}
	}
	
	@Test
	public void testDoParseForStatements(){
		
		filename = "test-suite-five.txt";
		
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR +filename;
		
		parse = new InputParse(path, "testsuite");
		
		parse.runParse();
		
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		assertTrue(suites.size() == 3);
		
		for(TestCase test : tests)
			for (ClassCode classcode : test.getClassCoverage()){
				for (Method method : classcode.getMethodCoverage()){
					assertTrue(method.getStatementCoverage().size() > 0);
				}
			}
	}
	
	
	
	@Test
	public void testValuePropertyPaths(){
		parse = new InputParse(path);
		parse.runParse();
		
		String value = "/home/samuel/AVL";
		
		assertEquals(value, parse.getValue(Property.APPLICATION) );
		
		value = "/home/samuel/AVL/src";
		
		assertEquals(value, parse.getValue(Property.CODE) );
		
		value = "";
		
		assertEquals(value, parse.getValue(Property.LIB) );
		
		value = "/home/samuel/AVL/src/tests";
		
		assertEquals(value, parse.getValue(Property.TESTS) );
		
		value = "";
		assertEquals(value, parse.getValue(Property.NEW_CODE) );
	
	}
	

}
