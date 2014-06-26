package integration;

import java.util.ArrayList;
import java.util.List;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;

/**
 * Resource to tests
 * 
 * @author Samuel T. C. Santos
 *
 */
public class IntegrationResource {
	
	public static List<List>  allSuites = new ArrayList<List>();
	
	/**
	 * Init the sample example to real suite.
	 */
	@SuppressWarnings("unchecked")
	public static void initSampleSuiteList() {
		TestSuite suite1 = new TestSuite("org", "SuiteA");
		
		TestCase tcA = new TestCase("com.SuiteA.testA");
		tcA.setSignature("com.suiteA.testA");
		ClassCode cc = new ClassCode("org","ClsA");
		Method m1 = new Method("method1");
		m1.addStatement(new Statement("5"));
		cc.addMethod(m1);
		tcA.addClassCoverage(cc);
		suite1.addTestCase(tcA);
		
		TestSuite suite2 = new TestSuite("org", "suiteB");
		
		TestCase tcB = new TestCase("com.suiteB.testB");
		tcB.addClassCoverage(cc);
		tcB.setSignature("com.suiteB.testB");
		suite2.addTestCase(tcB);
		
		TestCase tcC = new TestCase("com.suiteB.testC");
		tcC.setSignature("com.suiteC.testC");
		tcC.addClassCoverage(cc);
		suite2.addTestCase(tcC);
		
		allSuites.get(0).add(suite1);
		allSuites.get(1).add(suite2);
	}		
}
