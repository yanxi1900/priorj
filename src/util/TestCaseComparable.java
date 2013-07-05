package util;

import coverage.TestCase;

/**
 * This class is used to sorting a set of test case by weight values. 
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class TestCaseComparable implements Comparable {

	private double value;
	private TestCase testCase;
	
	/**
	 * The constructor to TestCaseComparable.
	 * 
	 * @param value a weight for this test case.
	 * @param testCase a test case.
	 */
	public TestCaseComparable(double value, TestCase testCase){
		this.value = value;
		this.testCase = testCase;
	}
	
	/**
	 * Get the value of weight for this test case.
	 * 
	 * @return the weight.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Return the test case.
	 * 
	 * @return a object type <code>TestCase</code>
	 */
	public TestCase getTestCase() {
		return testCase;
	}

	public int compareTo(Object object) {
		
		TestCaseComparable test = (TestCaseComparable) object;
			
		return (int) (value - test.getValue());
	}

}
