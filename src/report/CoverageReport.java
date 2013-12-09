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

import util.PathTo;
import util.WriteFile;
import coverage.ClassCode;
import coverage.Method;
import coverage.TestCase;
import coverage.TestSuite;
import coverage.Statement;

/**
 *  Class responsible to check and handle errors by checking if the 
 *  coverage is valid, contain all informations about coverage process.
 * 
 * @version 1.0 2013/04/11
 * @author Samuel T. C. Santos
 * 
 * @see <a href="http://code.google.com/p/util-team/">CoverageReport</a>
 * 
 */

public class CoverageReport {

	private List<TestCase> testsList;
    private List<TestSuite> suitesList;
    
    /**
     * Default construct.
     * 
     */
    public CoverageReport(){
		testsList = new ArrayList<TestCase>();
        suitesList = new ArrayList<TestSuite>();
	}
		
    
	/**
	 * <code>CoverageReport</code> constructor .
	 * 
	 * @param suites a list of objects of type <code>TestSuite</code>
	 */
	public CoverageReport(List<TestSuite> suites){
		testsList = new ArrayList<TestCase>();
        suitesList = new ArrayList<TestSuite>();
        
		buildReport(suites);    
	}
	
	/**
	 * Making validation suite based on the number of test cases.
	 * 
	 * @param suites a list of objects of type <code>TestSuite</code>
	 */
	public void buildReport(List<TestSuite> suites){
        List<String> testsJunit = JUnitReport.getTestNames();

        if(testsJunit.isEmpty()) // no validation!!!
        	return;
        
        for (TestSuite suite: suites){
            TestSuite newSuite = new TestSuite(suite.getPackageName(),suite.getName());

            for (TestCase test: suite.getTestCases()){
                String name = suite.toString().replace(".java","")+"."+test.getName();
                
                if (testsJunit.contains(name)){
                     //assign signatures
                     test.setSignature(suite.getPackageName()+"."+suite.getName().replace(".java","")+"."+test.getName());

                     //assign number methods and statements distinct
                     test.setNumberMethodsCoveredDistinct(test.getMethodCoverageDistinct().size());
                     test.setNumberStatementsCoverageDistinct(test.getStatementsCoverageDistinct().size());
                     
                     if (!testsList.contains(test))
                    	 testsList.add(test);
                     
                     newSuite.addTestCase(test);
                }
            }

            if (!newSuite.getTestCases().isEmpty() && !newSuite.getName().equals(""))
                suitesList.add(newSuite);
        }
                
	}
        
	/**
	 * Get the validated suites.
	 * 
	 * @return a list of objects of type <code>TestSuite</code>
	 */
	public List<TestCase> getTests(){
        return testsList;
	}
      
	/**
	 * Get the suites list.
	 * 
	 * @return a list of <code>TestSuite</code>.
	 */
    public List<TestSuite> getSuites(){
        return suitesList;
    }
    
    /**
     * Set an list of TestSuite.
     * 
     * @param suites
     * 	 list of objects type <code>TestSuite</code>
     */
    public void setSuites(List<TestSuite> suites){
    	this.suitesList = suites;
    }
    
    /**
     * This method build a code tree.
     * 
     * @return
     * 		A string with code tree representation.
     */
    private String buildReportTree(){
    	StringBuilder builder = new StringBuilder();
    	
    	buildTestSuiteReport(builder);
    	
    	return builder.toString();
    }

    /**
     * This method build a report from TestSuite list.
     * 
     * @param builder
     */
	private void buildTestSuiteReport(StringBuilder builder) {
		for (TestSuite suite: suitesList){
    		builder.append(suite.getName());
    		builder.append("\n");
    		builder.append(" ");
    		buildTestCaseReport(builder, suite);
    	}
	}

	/**
	 * This method build a report from Test Case list.
	 * 
	 * @param builder
	 * @param suite
	 */
	private void buildTestCaseReport(StringBuilder builder, TestSuite suite) {
		for (TestCase test : suite.getTestCases()){
			builder.append(test.getName());
			builder.append("\n");
			builder.append("  ");
			buildClassCodeReport(builder, test);
		}
	}

	/**
	 * This method build a report from <code>ClassCode</code> report list.
	 * @param builder
	 * @param test
	 */
	private void buildClassCodeReport(StringBuilder builder, TestCase test) {
		for(ClassCode classcode : test.getClassCoverage()){
			builder.append(classcode.getName());
			builder.append("\n");
			builder.append("   ");
			buildMethodReport(builder, classcode);
		}
	}

	/**
	 * This method build a report from <code>Method</code> list. 
	 */
	private void buildMethodReport(StringBuilder builder, ClassCode classcode) {
		for (Method method : classcode.getMethodCoverage()){
			builder.append(method.getName());
			builder.append("\n");
			builder.append("    ");
			buildStatementReport(builder, method);
			builder.append("\n");
		}
	}

	/**
	 * This method build a report from <code>Statement</code> List.
	 *
	 * @param builder
	 * 	A string builder.
	 * @param method
	 * 	A method.
	 */
	private void buildStatementReport(StringBuilder builder, Method method) {
		for(Statement statement : method.getUniqueStatements()){
			builder.append(statement.getLineNumber());
			builder.append(" ");
		}
	}
    
    /**
     *  This method return a code tree to coverage.
     */
    public String toString(){
    	
    	if (!suitesList.isEmpty()){
    		return buildReportTree();
    	}
    	
    	return "";
    }
    
    
    /**
     * This method build a file with data coverage.
     * 
     * @param suites
     * 		A TestSuite object.
     * @param filename
     * 		A name to file.
     */
    public void buildFileConfig(List<TestSuite> suites, String filename){
		StringBuilder fileConfig = new StringBuilder();
		
		fileConfig.append("*[SET_OF_TEST_CASE_-_TEST_SUITE_REPRESENTATION] \n\n");
		
        List<String> testsJunit = JUnitReport.getTestNames();

        if(testsJunit.isEmpty()) // no validation!!!
        	return;
        
        for (TestSuite suite: suites){
        	fileConfig.append("[test_suite_name]:[");
    		
        	TestSuite newSuite = new TestSuite(suite.getPackageName(),suite.getName());
            
            fileConfig.append(suite.getPackageName() + "," + suite.getName() + "] \n");            
            fileConfig.append("\t[test_case_name]:[");
            
            for (TestCase test: suite.getTestCases()){
                String name = suite.toString().replace(".java","")+"."+test.getName();
                
                fileConfig.append(test.getName() + "] \n");
                
                for (ClassCode classcode: test.getClassCoverage()) {
                	fileConfig.append("\t\t[class_code_name]:[");
                    
                	fileConfig.append(classcode.getPackageName() + "," + classcode.getName() + "] \n");
                	
                	for (Method method: classcode.getMethodCoverage()) {
                		fileConfig.append("\t\t\t[method_name]:[");
                		fileConfig.append(method.getName() + "] \n");
                		fileConfig.append("\t\t\t\t[statements_numbers]:[");
                		
                		//contador statements
                		int count = 0;
                		for (Statement statement: method.getStatementCoverage()) {
                			fileConfig.append(statement);
                			if (count < method.getStatementCoverage().size() - 1) {
                				fileConfig.append(",");
                				count++;
                			}		
                		}
                		
                		fileConfig.append("] \n");
                	}
                }
                
                if (testsJunit.contains(name)){
                     //assign signatures
                     test.setSignature(suite.getPackageName()+"."+suite.getName().replace(".java","")+"."+test.getName());

                     //assign number methods and statements distinct
                     test.setNumberMethodsCoveredDistinct(test.getMethodCoverageDistinct().size());
                     test.setNumberStatementsCoverageDistinct(test.getStatementsCoverageDistinct().size());
                     
                     if (!testsList.contains(test))
                    	 testsList.add(test);
                     
                     newSuite.addTestCase(test);
                }
            }

            if (!newSuite.getTestCases().isEmpty() && !newSuite.getName().equals(""))
            	suitesList.add(newSuite);
            }
        
        WriteFile writer = new WriteFile(PathTo.RESOURCES_PRIORITIZATION_FILES+ PathTo.SEPARATOR + filename);       
        writer.write(fileConfig.toString());   
	
    }
                
    
}
