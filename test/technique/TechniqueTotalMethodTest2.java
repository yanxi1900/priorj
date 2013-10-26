package technique;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;
import exception.EmptySetOfTestCaseException;

import util.PathTo;
import input.InputParse;

public class TechniqueTotalMethodTest2 {
	
	private String filename, filename2, filename3, filename4;
	
	private String path;

	private InputParse parse;
	
	private TechniqueAdditionalMethod ams;

	private String path4;

	private String path3;

	private String path2;

	private TechniqueAdditionalMethod ams2;

	private TechniqueAdditionalMethod ams3;
	
	@Before
	public void setUp() {
		filename = "test-suite-four.txt";
		filename2 = "test-suite-four(2).txt";
		filename3 = "test-suite-four(3).txt";
		filename4 = "SuiteConfig.txt";
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename;
		path2 = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename2;
		path3 = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename3;
		path4 = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename4;
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testTotalMethodAllTests() throws EmptySetOfTestCaseException {
		parse = new InputParse(path, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		ams = new TechniqueAdditionalMethod(tests);
		ams2 = new TechniqueAdditionalMethod(tests);
		ams3 = new TechniqueAdditionalMethod(tests);
		
		List<String> prioritization = ams.assingWeight();
		List<String> prioritization2 = ams2.assingWeight();
		List<String> prioritization3 = ams3.assingWeight();
		
		assertTrue(tests.toString().equals("[testA, testB, testC, testD, testE, testF, testG, testH, testI, testJ, testK, testL, testM, testN, testO]"));
		assertTrue(suites.toString().equals("[edu.ufcg.splab.pkg1.SuiteA, edu.ufcg.splab.pkg2.SuiteB, edu.ufcg.splab.pkg3.SuiteC]"));
		
		//Test position
		//1º
		assertTrue(ams.assingWeight().get(0).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testA"));
		assertTrue(ams2.assingWeight().get(0).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testA"));
		assertTrue(ams3.assingWeight().get(0).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testA"));		
		
		//2º
		assertTrue(ams.assingWeight().get(1).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testB"));
		assertTrue(ams2.assingWeight().get(1).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testB"));
		assertTrue(ams3.assingWeight().get(1).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testB"));
		
		//15º
		assertTrue(ams.assingWeight().get(14).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testJ"));
		assertTrue(ams2.assingWeight().get(14).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testJ"));
		assertTrue(ams3.assingWeight().get(14).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testJ"));
		
		//3º and 4º
		assertTrue(prioritization.get(2).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testO") || prioritization.get(2).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testM"));
		assertTrue(prioritization.get(3).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testM") || prioritization.get(3).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testO"));
		
		//5º, 6º, 7º, 8º, 9º, 10º and 11º
		assertTrue(prioritization.get(4).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(4).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(4).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(4).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(4).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(4).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(4).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(5).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(5).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(5).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(5).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(5).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(5).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(5).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(6).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(6).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(6).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(6).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(6).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(6).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(6).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(7).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(7).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(7).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(7).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(7).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(7).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(7).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(8).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(8).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(8).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(8).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(8).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(8).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(8).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(9).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(9).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(9).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(9).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(9).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(9).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(9).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));		
		assertTrue(prioritization.get(10).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testF") || prioritization.get(10).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testE") ||
				prioritization.get(10).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testD") || prioritization.get(10).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testG") ||
				prioritization.get(10).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testL") || prioritization.get(10).toString().equals("edu.ufcg.splab.pkg3.SuiteC.testN") ||
				prioritization.get(10).toString().equals("edu.ufcg.splab.pkg1.SuiteA.testC"));
		
		//12º, 13º and 14º
		assertTrue(prioritization.get(11).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testH") || prioritization.get(11).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testI") ||
				prioritization.get(11).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testK"));		
		assertTrue(prioritization.get(12).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testH") || prioritization.get(12).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testI") ||
				prioritization.get(12).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testK"));		
		assertTrue(prioritization.get(13).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testH") || prioritization.get(13).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testI") ||
				prioritization.get(13).toString().equals("edu.ufcg.splab.pkg2.SuiteB.testK"));
		
		//Test for Random parts
		assertFalse(prioritization.equals(prioritization2));
		assertFalse(prioritization.equals(prioritization3));
		assertFalse(prioritization2.equals(prioritization3));
		
//		System.out.println(tests);
//		System.out.println();
//		System.out.println(suites);
//		System.out.println();
//		System.out.println(amc.assingWeight());
	}
	
	@Test
	public void tetsTotalMethodOneTest() throws EmptySetOfTestCaseException {
		parse = new InputParse(path2, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		ams = new TechniqueAdditionalMethod(tests);
		
		assertTrue(tests.toString().equals("[testA]"));
		assertTrue(suites.toString().equals("[edu.ufcg.splab.pkg1.SuiteA]"));
		assertTrue(ams.assingWeight().toString().equals("[edu.ufcg.splab.pkg1.SuiteA.testA]"));
		
//		System.out.println(tests);
//		System.out.println();
//		System.out.println(suites);
//		System.out.println();
//		System.out.println(amc.assingWeight());
	}
	
	@Test
	public void testTotalMethodTwoTests() throws EmptySetOfTestCaseException {
		parse = new InputParse(path3, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		ams = new TechniqueAdditionalMethod(tests);
		
		assertTrue(tests.toString().equals("[testA, testB]"));
		assertTrue(suites.toString().equals("[edu.ufcg.splab.pkg1.SuiteA]"));
		assertTrue(ams.assingWeight().toString().equals("[edu.ufcg.splab.pkg1.SuiteA.testA, edu.ufcg.splab.pkg1.SuiteA.testB]"));

//		System.out.println(tests);
//		System.out.println();
//		System.out.println(suites);
//		System.out.println();
//		System.out.println(amc.assingWeight());
	}
	
	@Test
	public void testAdditionalMethodJMock() throws EmptySetOfTestCaseException{
		parse = new InputParse(path4, "testsuite");
		parse.runParse();
		List<TestSuite> suites = parse.getResultAsSuite();
		List<TestCase> tests = parse.getResultAsTestCase();
		
		ams = new TechniqueAdditionalMethod(tests);
		
//		assertTrue();
//		assertTrue();
//		assertTrue();

		System.out.println(tests);
		System.out.println();
		System.out.println(suites);
		System.out.println();
		System.out.println(ams.assingWeight());
	}

}
