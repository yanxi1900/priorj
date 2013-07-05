package report;

import java.util.ArrayList;
import java.util.List;

import util.Settings;

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
	public String genereteCoverageReport(){
            addAllTestCases();
            
            
            StringBuilder line = new StringBuilder();
            
            for (int i=0; i<130; i++)
                line.append(".");
            

            StringBuilder builder = new StringBuilder();
            builder.append(getCabecalho());

            int count = 0;

            for (TestSuite suite : suites) {
                String suiteName = suite.toString();
                for (TestCase tc : suite.getTestCases()) {

                    String newTest = suiteName.replace(".java", ".") + tc.getName();

                    count++;
                    builder.append(" ");
                    builder.append(String.valueOf(count));
                    builder.append(" - ");
                    builder.append(newTest);
                    builder.append("()");
                    builder.append(Settings.NEWLINE);


                    double percent = percentValue(getCountUniqueClassCoverage(tc), classes.size());
                    builder.append(" CC = " );
                    builder.append(String.valueOf(getCountUniqueClassCoverage(tc)));
                    builder.append("\t\t");
                    builder.append("Coverage (");
                    builder.append(String.valueOf(percent));

                    builder.append( "%)");
                    builder.append(Settings.NEWLINE);


                    percent = percentValue(getCountUniqueMethodCoverage(tc), methods.size());

                    builder.append(" MC = " );
                    builder.append(getCountUniqueMethodCoverage(tc));
                    builder.append("\t\t");
                    builder.append("Coverage (");
                    builder.append(String.valueOf(percent));

                    builder.append("%)");
                    builder.append(Settings.NEWLINE);

                    percent = percentValue(getCountUniqueStatementCoverage(tc), statements.size());

                    builder.append(" SC = " );
                    builder.append(getCountUniqueStatementCoverage(tc));
                    builder.append("\t\t");
                    builder.append("Coverage (");
                    builder.append( String.valueOf(percent));
                    builder.append("%)");
                    builder.append(Settings.NEWLINE);
                    builder.append(Settings.NEWLINE);

                }

        }

        builder.append(line.toString());
        
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
            builder.append(Settings.NEWLINE);
            builder.append("	             Name Test Suite: ");
            builder.append(this.suites.get(0).getName());
            
            builder.append(Settings.NEWLINE);
            
            builder.append("	        Number of test cases: ");
            builder.append(String.valueOf(this.testCases.size()));
            
            builder.append(Settings.NEWLINE);

            builder.append(line.toString());
            builder.append(Settings.NEWLINE);
            
            builder.append("CC = Classes Covered \t");
            builder.append("|MC = Methods Covered \t");
            
            builder.append("|SC = Statements Covered" );
            builder.append(Settings.NEWLINE);
            
            builder.append("Number of Classes = ");
            builder.append(String.valueOf(totalClasses));
            builder.append("\t|Number of Methods = ");
            builder.append(String.valueOf(totalMethods));
            builder.append("\t|Number of Statements = ");
            builder.append(String.valueOf(totalStatements));
            
            builder.append(Settings.NEWLINE);
            
            builder.append(line.toString());
            
            builder.append(Settings.NEWLINE);
            
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