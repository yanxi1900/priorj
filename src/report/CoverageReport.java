package report;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


import java.util.ArrayList;
import java.util.List;

import coverage.TestCase;
import coverage.TestSuite;

/**
 * Retrieve the coverage report
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */

public class CoverageReport {
    
	private List<TestSuite> suites;
	
    /**
     * Create a coverage report to read coverage data from a project.
     * 
     * @param localbase
     *  path to local store data 
     * @param folderName
     * 	folder to selected project in workspace.
     * @throws Exception
     */
    public CoverageReport(String localbase, String folderName) throws Exception{
  //  	PriorJIO io = new PriorJIO(localbase);
    //	suites = io.openCoverageReport(folderName);
    }
    
    /**
     * Get the list of suites.
     * 
     * @return
     */
    public List<TestSuite> getSuites(){
    	return suites;
    }
    
    /**
     * Get the list of tests and initialize informations about coverage.
     * 
     * @return
     */
    public List<TestCase> getTestCases(){
    	List<TestCase> tests = new ArrayList<TestCase>();
    	for (TestSuite suite: suites){
    		for (TestCase test : suite.getTestCases()){
    			addSignature(suite, test);
    			addNumberOfMethods(test);
    			addNumberOfStatements(test);
    			tests.add(test);
    		}
    	}
    		
    	return tests;
    }

    /**
     * Add signature to test.
     * 
     * @param suite
     * @param test
     */
	private void addSignature(TestSuite suite, TestCase test) {
		String signature = createSignature(suite, test);
		
		test.setSignature(signature);
	}

	/**
	 * Add number of methods covered by test.
	 * 
	 * @param test
	 */
	private void addNumberOfMethods(TestCase test) {
		int numberMethods = test.getNumberMethodsCoveredDistinct(); 
		test.setNumberMethodsCoveredDistinct(numberMethods);
	}
	/**
	 * Add number of statements covered by test.
	 * @param test
	 */
	private void addNumberOfStatements(TestCase test) {
		int numberStatements = test.getNumberStatementsCoverageDistinct();
		test.setNumberStatementsCoverageDistinct(numberStatements);
	}
    
    /**
     * Create the signature to test.
     * 
     * @param suite
     * @param test
     * @return
     */
    private String createSignature(TestSuite suite, TestCase test){
    	return suite.getPackageName() + "." + suite.getName().replace(".java", "") + "." + test.getName();
    }
}
