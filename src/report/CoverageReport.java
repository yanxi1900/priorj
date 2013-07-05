package report;


import java.util.ArrayList;
import java.util.List;

import coverage.TestCase;
import coverage.TestSuite;



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
	 * <code>CoverageReport</code> constructor .
	 * 
	 * @param suites a list of objects of type <code>TestSuite</code>
	 */
	public CoverageReport(List<TestSuite> suites){
        testsList = new ArrayList<TestCase>();
        suitesList = new ArrayList<TestSuite>();
                
		buildReport(suites);
	}
	
	public CoverageReport(){
		testsList = new ArrayList<TestCase>();
        suitesList = new ArrayList<TestSuite>();
	}
		
	/**
	 * Making validation suite based on the number of test cases.
	 * 
	 * @param suites a list of objects of type <code>TestSuite</code>
	 */
	public void buildReport(List<TestSuite> suites){
        List<String> testsJunit = JUnitReport.getTestNames();

        for (TestSuite suite: suites){
            TestSuite newSuite = new TestSuite(suite.getPackageName(),suite.getName());

            for (TestCase test: suite.getTestCases()){
                String name = suite.toString().replace(".java","")+"."+test.getName();
                if (testsJunit.contains(name)){
                     //assing signatures
                     test.setSignature(suite.getPackageName()+"."+suite.getName().replace(".java","")+"."+test.getName());

                     //assing number methods and statements distincts
                     test.setNumberMethodsCoveredDistinct(test.getMethodCoverageDistinct().size());
                     test.setNumberStatementsCoverageDistinct(test.getStatementsCoverageDistinct().size());

                     testsList.add(test);
                     newSuite.addTestCase(test);
                }
            }

            if (!newSuite.getTestCases().isEmpty() && !newSuite.getName().equals(""))
                suitesList.add(newSuite);
        }
                
       //SaveFile.saveCoverageReport(generateCoverageReportFile());
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
     * @param suites an list of objects type <code>TestSuite</code>
     */
    public void setSuites(List<TestSuite> suites){
    	this.suitesList = suites;
    }
    
//    public String generateCoverageReportFile() {
//        GenerateCoverageReport coverage = new GenerateCoverageReport(getSuites());
//        String code = coverage.genereteCoverageReport();
//        //free space
//        suitesList.clear();
//        report = code;
//        return code;
//    }
      	
}
