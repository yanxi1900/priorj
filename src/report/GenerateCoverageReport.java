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
          		builder.append("\t\tclasses    : "+getCountUniqueClassCoverage(testcase)+",\n");
          		builder.append("\t\tmethods    : "+getCountUniqueMethodCoverage(testcase)+",\n");
          		builder.append("\t\tstatements : "+getCountUniqueStatementCoverage(testcase));
          		builder.append("\n\t});\n");
          	}
          }
          
          builder.append("}\n\n");
          
          builder.append("function fillTable(){\n");
          builder.append("\t$('#myTable').append('<thead>');\n");
          builder.append("\tvar header = '<tr>';\n");
          builder.append("\theader += cellHeader('Test Suites (' + coverageGlobal.suites +')');\n");
          builder.append("\theader += cellHeader('Test Cases (' + coverageGlobal.testcases + ')');\n");
          builder.append("\theader += cellHeader('Classes (' + coverageGlobal.classes + ')');\n");
          builder.append("\theader += cellHeader('Methods (' + coverageGlobal.methods + ')');\n");
          builder.append("\theader += cellHeader('Statements (' + coverageGlobal.statements+')');\n");
          builder.append("\theader += '</tr>';\n");
          builder.append("\t$('#myTable').append(header);\n");
	
          builder.append("\t$('#myTable').append('</thead>');\n");
          builder.append("\t$('#myTable').append('<tbody>');\n");
	      	
          builder.append("\tfor (var i=0; i < coverage.length; i++){\n");
          builder.append("\t\tvar row = '<tr>';");
          builder.append("\t\trow+= cellBody(coverage[i].testsuite);\n");
          builder.append("\t\trow+= cellBody(coverage[i].testcase);\n");
  		
          builder.append("\t\tvar value = coverage[i].classes;\n");
          builder.append("\t\tvar total = coverageGlobal.classes;\n");
          builder.append("\t\tvar percent = percentage(value,total);\n");
          builder.append("\t\trow+= cellBody(value + ' (' + percent +'%)');\n");
  		
          builder.append("\t\tvalue = coverage[i].methods;\n");
          builder.append("\t\ttotal = coverageGlobal.methods;\n");
          builder.append("\t\tpercent = percentage(value, total);\n");
          builder.append("\t\trow+= cellBody(value + ' (' + percent +'%)');\n");
  		
          builder.append("\t\tvalue = coverage[i].statements;\n");
          builder.append("\t\ttotal = coverageGlobal.statements;\n");
          builder.append("\t\tpercent = percentage(value, total);\n");
          builder.append("\t\trow+= cellBody(value + ' (' + percent +'%)');\n");
          builder.append("\t\trow += '</tr>';\n");
          builder.append("\t\t$('#myTable').append(row);\n");
          
          builder.append("\t}\n");
          builder.append("\t$('#myTable').append('</tbody>');\n");
          builder.append("}\n\n");
                  
          builder.append("function cellHeader(title){\n");
          builder.append("\t\treturn '<th>' +title +'</th>';\n");
          builder.append("}\n\n");
		
          builder.append("function cellBody(content){\n");
          builder.append("\treturn '<td>' +content +'</td>';\n");
          builder.append("}\n\n");
		
          builder.append("function percentage(value, total){\n");
          builder.append("\tvar percent = value * 100 / total;\n");
          builder.append("\treturn percent.toFixed(2);\n");
          builder.append("}\n\n");
		
          builder.append("function getSuiteSize(){\n");
          builder.append("\treturn coverage.length;\n");
          builder.append("}\n");

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