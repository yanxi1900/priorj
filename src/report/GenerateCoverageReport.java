package report;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2014  SPLab
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

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;

/**
 * This class generate a simple coverage report.
 * 
 * @author Samuel T. C. dos Santos
 * @verion 1.0
 * 
 */
public class GenerateCoverageReport {
    
	private List<TestSuite> suites;
	private List<String> testCases;
	private List<String> classes;
	private List<String> methods;
	private List<String> statements;
	private String newline = System.getProperty("line.separator");
    
    /**
     * Constructor.
     * 
     * @param suites
     *      A list of Test suites.
     */
	public GenerateCoverageReport(List<TestSuite> suites){
		this.suites = suites;
		this.testCases = new ArrayList<String>();
		this.classes = new ArrayList<String>();
		this.methods = new ArrayList<String>();
		this.statements = new ArrayList<String>();
	}
	
    /**
     * Get the coverage report generated.
     * 
     * @return
     *      The coverage report.
     */
	public String generateCoverageReport(){
            addAllTestCases();
            StringBuilder builder = new StringBuilder();
            
            builder.append("var coverage = [];\n\n");
            builder.append("var coverageGlobal = {\n");
            builder.append("\tsuites: "+suites.size()+",\n");
            builder.append("\ttestcases: "+testCases.size()+",\n");
            builder.append("\tclasses: "+classes.size()+",\n");
            builder.append("\tmethods: "+methods.size()+",\n");
            builder.append("\tstatements: "+statements.size());
            builder.append("\n};\n\n");
            
            builder.append("$(function(){\n");
            builder.append("\tloadData();\n");
            builder.append("\tfillTable();\n");
            builder.append("});\n\n");
            
            builder.append("function loadData(){\n");
            
          for (TestSuite suite : suites) {
        	String suiteName = suite.toString();
          	for (TestCase testcase : suite.getTestCases()) {
          		builder.append("\tcoverage.push({\n");
          		builder.append("\t\ttestsuite  : '"+suiteName+"',\n");
          		builder.append("\t\ttestcase   : '"+testcase.getName()+"',\n");
          	}
          }
          
          builder.append("}\n\n");
          
            
//            int count = 0;
//
//            for (TestSuite suite : suites) {
//                String suiteName = suite.toString();
//                for (TestCase tc : suite.getTestCases()) {
//
//                    String newTest = suiteName.replace(".java", ".") + tc.getName();
//
//                    count++;
//                    builder.append(" ");
//                    builder.append(String.valueOf(count));
//                    builder.append(" - ");
//                    builder.append(newTest);
//                    builder.append("()");
//                    builder.append(newline);
//
//
//                    double percent = percentValue(getCountUniqueClassCoverage(tc), classes.size());
//                    builder.append(" CC = " );
//                    builder.append(String.valueOf(getCountUniqueClassCoverage(tc)));
//                    builder.append("\t\t");
//                    builder.append("Coverage (");
//                    builder.append(String.valueOf(percent));
//
//                    builder.append( "%)");
//                    builder.append(newline);
//
//                    percent = percentValue(getCountUniqueMethodCoverage(tc), methods.size());
//
//                    builder.append(" MC = " );
//                    builder.append(getCountUniqueMethodCoverage(tc));
//                    builder.append("\t\t");
//                    builder.append("Coverage (");
//                    builder.append(String.valueOf(percent));
//
//                    builder.append("%)");
//                    builder.append(newline);
//
//                    percent = percentValue(getCountUniqueStatementCoverage(tc), statements.size());
//
//                    builder.append(" SC = " );
//                    builder.append(getCountUniqueStatementCoverage(tc));
//                    builder.append("\t\t");
//                    builder.append("Coverage (");
//                    builder.append( String.valueOf(percent));
//                    builder.append("%)");
//                    builder.append(newline);
//                    builder.append(newline);
//                }
//        }

        
        return builder.toString();          
	}
	
    /**
    * Calculate the percentage of the value.
    * @param valor
    * @param total
    * @return the percentage.
    */
   private double percentValue(int valor, int total) {
	  return valor * 100 / total;
   }
        
	private String getCabecalho(){
            StringBuilder builder = new StringBuilder();
            
            StringBuilder line = new StringBuilder();
            
            for (int i=0; i<130; i++)
                line.append(".");
            
            int totalClasses = classes.size();
            int totalMethods = methods.size();
            int totalStatements = statements.size();

            builder.append(".................................:   PriorJ - Coverage Report");
            builder.append("  :.......................................");
            builder.append(newline);
            builder.append("	             Name Test Suite: ");
            builder.append(this.suites.get(0).getName());
            
            builder.append(newline);
            
            builder.append("	        Number of test cases: ");
            builder.append(String.valueOf(this.testCases.size()));
            
            builder.append(newline);

            builder.append(line.toString());
            builder.append(newline);
            
            builder.append("CC = Classes Covered \t");
            builder.append("|MC = Methods Covered \t");
            
            builder.append("|SC = Statements Covered" );
            builder.append(newline);
            
            builder.append("Number of Classes = ");
            builder.append(String.valueOf(totalClasses));
            builder.append("\t|Number of Methods = ");
            builder.append(String.valueOf(totalMethods));
            builder.append("\t|Number of Statements = ");
            builder.append(String.valueOf(totalStatements));
            
            builder.append(newline);
            
            builder.append(line.toString());
            
            builder.append(newline);
            
            return builder.toString();
	}
	
	private void addAllTestCases(){
		if(this.testCases.size() != 0) return;
		for (TestSuite suite : suites) {
			String nameSuite = suite.toString();
			List<TestCase> tcs = suite.getTestCases();
			for (TestCase testCase : tcs) {
				String str = nameSuite+"."+testCase.getName();
				this.testCases.add(str);
				addAllClassCovered(testCase);
			}
		}
	}
	
	private void addAllClassCovered(TestCase tc){
		for (ClassCode clazz : tc.getClassCoverage()) {
			String className = clazz.toString();
			if(!this.classes.contains(className)){
				this.classes.add(className);
			}
			addAllMethodsCoverage(clazz);
		}
	}
	
	private void addAllMethodsCoverage(ClassCode clazz) {
		for (Method method : clazz.getMethodCoverage()) {
			String methodName = clazz.toString()+"."+method.getName();
			if(!this.methods.contains(methodName)){
				this.methods.add(methodName);
			}
			addAllStatementCovered(methodName, method);
			
		}
	}

	private void addAllStatementCovered(String methodName, Method method) {
		for (Statement sttm : method.getStatementCoverage()) {
			String stmName = methodName+"."+sttm.getLineNumber();
			if(!this.statements.contains(stmName)){
				this.statements.add(stmName);
			}
		}
		
	}
	
	public int getCountUniqueClassCoverage(TestCase tc){
		int count = 0;
		List<String> classes = new ArrayList<String>();
		for (String clazz : tc.getClassCodeCoverage()) {
			if(!classes.contains(clazz)){
				classes.add(clazz);
				count++;
			}
		}
		return count;
	}
	
	public int getCountUniqueMethodCoverage(TestCase tc){
		int count = 0;
		List<String> methods = new ArrayList<String>();
		for (String method : tc.getMethodCoverage()) {
			if(!methods.contains(method)){
				methods.add(method);
				count++;
			}
		}
		return count;
	}
	
	public int getCountUniqueStatementCoverage(TestCase tc){
		int count = 0;
		List<String> sttms = new ArrayList<String>();
		for (String sttm : tc.getStatementsCoverage()) {
			if(!sttms.contains(sttm)){
				sttms.add(sttm);
				count++;
			}
		}
		return count;
	}
}