package report;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;

public class CoverageReportTest {

	private CoverageReport coverageReport;
	private List<TestSuite> suites;
	
	@Before 
	public void setUp(){
		suites = new LinkedList<TestSuite>();
		coverageReport = new CoverageReport();
	}
	
	@Test
	public void testWhenSuiteIsEmpty(){
		List<TestCase> tests = coverageReport.getTests();
		
		assertTrue(tests.isEmpty());
	}
	
	@Test
	public void testRemoveRepetedTestCase() {
		
		TestSuite suite1 = new TestSuite("pkg1", "A");
		TestSuite suite2 = new TestSuite("pkg1", "A");
		
		String [] testsSuite1 = {"testA", "testB", "testB", "testC", "testD"};
		String [] testsSuite2 = {"testA", "testD", "testC", "testC", "testD"};
		
		for (String testName: testsSuite1){
			suite1.addTestCase(new TestCase(testName));
		}
		
		for (String testName: testsSuite2){
			suite2.addTestCase(new TestCase(testName));
		}
		
		//suite1 has 4 tests distinct: testA, testB, testB, testC, testD
		suites.add(suite1);
		
		//suite2 has 3 tests distinct: testA, testC, testD
		suites.add(suite2);
		
		coverageReport = new CoverageReport(suites);
		
		List<TestCase> tests = coverageReport.getTests();
		
		assertTrue(tests.size() == 7);
	}

}
