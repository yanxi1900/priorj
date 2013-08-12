package main;

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
import java.util.List;

import project.JUnitVersionEnum;
import report.CodeTree;
import report.CoverageReport;
import report.JUnitReport;

import technique.TechniquesEnum;

import coverage.TestCase;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.DirectoryNotExistException;
import exception.EmptyPathException;
import exception.EmptySetOfTestCaseException;
import exception.InstrumentationUnrealizedException;

/**
 * PriorJ is a interface for JUnit test case prioritization provide
 * a set of methods to instrumentation, coverage analysis, prioritization of 
 * suites tests and report of execution test, coverage and 
 * generation of prioritized suite.
 *  
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public interface PriorJ {
    
    /**
        * This method instrument a application code.
        * Instrumentation is the first step in the prioritization process.
        * 
        * @throws InstrumentatationUnrealizedException
        *      When try access file not found or paths wrong.
        */
    public void runInstrumentation() throws InstrumentationUnrealizedException;

    /**
        * This method does the coverage of the tests suite using
        * AspectJ to capture the log and sequence of execution.
        * 
        * This step need before which all code are instrumented.
        * 
        * @throws Exception
        *        Tests failed, aspect file not found or if not Instrumented.
        */
    public void runCoverage() throws CoverageUnrealizedException;

    /**
        * This step read the XML file generete in the Run coverage step.
        * 
        * It's necessary which run instrumentation and run coverage has been done.
        * 
        * @throws Exception 
        *          coverageReport.xml not found or not has data.
        */
    public void runReadLog() throws CannotReadLogFileException;


    /**
        * Method which performing the coverage.
        * 
        * @param pathApp 
        * 			The path of the application.
        * @param pathCode 
        * 			The path of the source code.
        * @param pathLib 
        * 			The path of the libraries.
        * @param pathTest 
        * 			The path of the test cases.
        */
    public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest)  throws EmptyPathException, DirectoryNotExistException ;

    /**
        * Get the list of TestCases;
        * 
        * @return 
        * 		An list of Objects <code>TestCase</code>.
        */
    public List<TestCase> getTestCases();

    /**
        * Get the <code>CoverageReport</code>.
        *  
        * @return 
        * 		An <code>CoverageReport</code> with information about tests coverage.
        * 
        */
    public CoverageReport getCoverageReport();

    /**
        * This method run the prioritization to multiple techniques.
        * 
        */
    public void runPrioritization() throws Exception ;

    /**
        * Prioritization of a test suite using technique Additional Method Coverage.
        * 
        * @param tests an list of <code>TestCase</code>.
        * 
        * @param coverage
        * @return
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationAMC( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Prioritization of a test suite using technique Additional Statement Coverage.
        * 
        * @param tests
        * 			Set of test case.
        * @return
        * 			list of string in the prioritized order.
        * 
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationASC( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Prioritization of a test suite using technique Total Method Coverage.
        * 
        * @param tests 
        * 		Set of test case.
        * 
        * @return
        * 		List of string in the prioritized order.
        * 
        * @throws EmptySetOfTestCaseException 
        * 		When the set of test is empty.
        */		
    public List<String> runPrioritizationTMC( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Prioritization of a test suite using technique Total Statement Coverage.
        * 
        * @param tests
        * 			Set of test case.
        * @param coverage
        * 			List of string in the prioritized order.
        * @return
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationTSC( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Prioritization of a test suite using technique Random.
        * 
        * 
        * @param tests
        * 			Set of test case.
        * @return
        * 			List of string in the prioritized order.
        * 			
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationRD( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Prioritization of a test suite using Total Echelon Technique (Changed Blocks).
        * 
        * @param tests
        * 			Set of test case.
        * @param pathCodeNew
        * 		the path to new application
        * @param pathCodeOld
        * 		the path to old version 
        * @return
        * 			List of string in the prioritized order.
        * 
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationCB( List<TestCase> tests,  String pathNewCode, String pathCodeOld) throws EmptySetOfTestCaseException;


    /**
        * Prioritization of a test suite using Refactoring Based Approaching Technique (RBA).
        * 
        * @param tests
        * 			Set of test case.
        * @return
        * 			List of string in the prioritized order.
        * @throws EmptySetOfTestCaseException 
        * 			When the set of test is empty.
        */
    public List<String> runPrioritizationRBA( List<TestCase> tests) throws EmptySetOfTestCaseException;

    /**
        * Set the list of Prioritization Techniques.
        * 
        * @param techniques
        *      A list of Techniques.
        */
    public void SetPrioritizationTechniques(List<TechniquesEnum> techniques);

    /**
        * This method add a new technique to prioritization process.
        * 
        * @param technique
        *      A Prioritization techinique.
        */
    public void addPrioritizationTechnique(TechniquesEnum technique);

    /**
        * This method remove a Prioritization technique.
        * 
        * @param technique
        *      A technique
        */
    public void removePrioritizationTechnique(TechniquesEnum technique);


    /**
        * Generate a simple Report with information about test coverage.
        * 
        * @param coverage
        * 			The Object <code>CoverageReport</code>.
        * @return
        * 			An String with the report.
        */
    public String getSimplifiedCoverageReport(CoverageReport coverage);

    /**
        * Read the coverageReport.txt and return the report from file.
        * 
        * @return 
        *      The coverage report saved in the file.
        */
        public String openSimpleCoverageReport();

    /**
        * This method create and return a CodeTree Object, this object
        * provide a tree with the coverage log track.
        * 
        * @param coverage
        * 			The Object <code>CoverageReport</code>.
        * @return
        * 			An Object type <code>CodeTree</code>
        */
    public CodeTree getTestCoverageTree(CoverageReport coverage);
    /**
        * This method read the logFile and return a Code Tree if the coverage is done.
        * 
        * If the coverage not is done, this method should be throw a exception
        * NullPointerException.
        * 
        * @return
        *      The <code>CodeTree</code> or an NullPointerException.
        */
    public CodeTree openTestCoverageTree();



    /**
        * Save the Tests in order of prioritization to hard disc.
        * 
        * @param tests 
        * 	    A list of prioritized test cases.
        * @param  technique
        *          The technique used.
        * 
        */
    public void savePrioritizationOrder(List<String> prioritizedTests,  TechniquesEnum technique);

    /**
        * Open the prioritized order saved into the file.
        * 
        * @param technique
        *          the technique used.
        * @return 
        *          The content saved into the file.                          
        */
    public String openPrioritizationOrder( TechniquesEnum technique);

    /**
        * JUnit report with information about the tests execution.
        * 
        * @param type 
        * 			The type of the format report.
        * 
        * @return 
        * 			An Object <code>JunitReport</code> with the report.
        */
    public JUnitReport getJUnitReport();
    /**
        * A string with the result of JUnit execution.
        * 
        * @return
        * 		A string with report.
        */
    public String simpleJUnitReport();

    /**
        * Calculate the APFD value. Given an list of faults which represent the number
        * of faults, and an list of list relating the list of faults
        * and an list of test cases failed related with this fault. 
        * 
        * faults.get(0) is a set of failed tests, example: ["test1, test2, test3, ..., testN"]
        *  
        * @param testCaseCombination 
        * 			List of list with test cases names.
        * 
        * @param tests 
        * 			List of prioritized test cases.
        * 
        * @return 
        * 			The APFD value.
        */
    public float calculateAPFD(List<String> tests, List<List<String>> testCaseCombination);

    /**
        * Generate and Save the prioritized suite code.
        * 
        * @param tests
        *         A list of Test Cases.
        * 
        * @param technique 
        * 			The technique name.
        * 
        */
    public void saveJavaPrioritizedSuite(List<String> tests,  TechniquesEnum technique);

    /**
        * Set the JUnit version for tests.
        * 
        * @param version 
        * 			JUnitVersionEnum.JUNIT3 or JUnitVersionEnum.JUNIT4
        */
    public void setJUnitVersion (JUnitVersionEnum version);

    /**
        * Get the list of failed test cases.
        * 
        * @return
        * 			A list of failed test cases.
        */
    public List<String> getFailedTestCase();
	
        
    /**
    * Set the path application.
    * 
    */
   public void setPathApplication(String pathApplication);
   
   /**
    * Get the path application.
    * 
    * @return 
    *   the path application.
    */
   public String getPathApplication();
   
   /**
    * Set the path code.
    *
    */
   public void setPathCode(String pathCode);
   
   /**
    * Get the path code.
    * 
    * @return
    *       The path code.
    */
   public String getPathCode();
   
   /**
    * Set the path Libraries.
    * 
    */
   public void setPathLibraries(String pathLibraries);
   
   /**
    * Get the path libraries.
    * 
    * @return
    *       The path libraries.
    */
   public String getPathLibraries();
   /**
    * Set the path tests.
    * 
    */
   public void setPathTests(String pathTests);
   
   /**
    * Get the path Test.
    * @return 
    *       The path test.
    */
   public String getPathTests();
   /**
    * Get the path of new code application.
    * 
    * @return
    *       The new path code.
    */
   public String getPathCodeNew();
   /**
    * Set the path of new application.
    * 
    */
   public void setPathCodeNew(String pathCodeNew);
   
   /**
    * This method get the total path code.
    * 
    * @return
    * 	path app + path code
    */
   public String getTotalPathCode();
   
   /**
    * This method get the total path test.
    * @return
    * 	path code + path test
    */
	public String getTotalPathTest();
   
   /**
    * This method is used by changed blocks technique, to verify
    * modifications between two application version.
    * 
    * @return 
    *   A list of String with the modifications found.
    */
   public List<String> blockAffected();
  
   /**
    * This method do get the prioritization techniques list.
    * 
    * @return 
    *       A list of techniques.
    */
   public List<TechniquesEnum> getPrioritizationTechniques();
   
   /**
    * This method used by Refactoring based approach technique to rename method.
    * 
    * @param pathApp
    *       The path application.
    * @param className
    *       The class name.
    * @param methodName
    *       The method name.
    * @param newMethodName 
    *       the new method name.
    */
   public void runRenameMethod(String pathApp, String className, String methodName, String newMethodName);

   /**
    * This method used by Refactoring based approach technique to extract method.
    * 
    * @param pathApp
    *       The path application.
    * @param originMethodName
    *       the origin method name.
    * @param className
    *       The class name.
    * @param methodName
    *       The method name.
    * @param newMethodName
    *       The new Method name.
    * @param beginLine
    *       The begin line.
    * @param endLine 
    *       The end line.
    */
   public void runExtractMethod(String pathApp, String originMethodName, String className,
                            String methodName, String newMethodName, int beginLine, int endLine);
   
   /**
    * This method used by Refactoring based approach technique to Move Method refactoring.
    * 
    * @param pathApp
    *       The path application
    * @param classOneName
    *       The class one name.
    * @param classTwoName
    *       The class two name.
    * @param methodName 
    *       The method name.
    */
   public void runMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName);
 
   /**
    * This method used by Refactoring based approach technique to Pull Up Method refactoring.
    * 
    * @param pathApp
    *       The path application.
    * @param classOneName
    *       The class one name.
    * @param classTwoName
    *       The class two name.
    * @param methodName 
    *       The method name.
    */
   public void runPullUpMethod(String pathApp, String classOneName, String classTwoName, String methodName);
   
   /**
    * This method used by Refactoring based approach technique to Pull Up field refactoring.
    * 
    * @param pathApp
    *       The path application.
    * @param classOneName
    *       The class one name.
    * @param classTwoName
    *       The class two name.
    * @param fieldName 
    *       The field name.
    */
   public void runPullUpField(String pathApp, String classOneName, String classTwoName, String fieldName);
   
   /**
    * This method used by Refactoring based approach technique to add parameter refactoring.
    * 
    * @param pathApp
    *       The path application.
    * @param className
    *       The class name.
    * @param methodName 
    *       The method name.
    */
   public void runAddParameter(String pathApp, String className, String methodName);
   
   /**
    * This method say if the instrumentation is done.
    * 
    * @return
    * 	true or false
    */
   public boolean isInstrumented();
   
   /**
    * This method say if the coverage is done.
    * 
    * @return
    * 	true or false
    */
   public boolean isCovered();
   
   /**
    * This method say if the log file is created and can to read it.
    * 
    * @return
    * 	true or false.
    */
   public boolean isReadLog();
   
   /**
    * This method say if the prioritization is done.
    * 
    * @return
    * 	true or false
    */
   public boolean isPrioritized();
   
   /**
    * This method generate a list of java prioritized suite code from a list of
    * list with in order of test cases prioritization.
    *  
    * @param suiteName
    * 	The suite name.
    * @param suites
    * 	The suites, a list of list with test cases names.
    * @param filenames
    * 	The suites names.
    * @param packageName
    * 	The package name.
    * @param 
    * 	the selection size.
    * 
    * @return
    * 	A list with java prioritized suite code.
    */
   public List<String> generateSuiteSelection(String suiteName, List<List<String>> suites, List<String> filenames, String packageName, int size);
   
   /**
    *  This method is used to save a java test suite to indicated local
    * many test suites is saved together with specified selection size.
    * 
    *
    * @param path
    * 	The local to save
    * @param suiteCode
    * 	The suite code.
    * @param suiteNames
    * 	The list of suites names
    * @param suiteUserName
    * 	The suite name given by user
    */
   public void saveSuiteSelection(String path, List<String> suiteCode, List<String> suiteNames, String suiteUserName);
   
   /**
    * This method say if the techniques was selected.
    * 
    * @return
    * 	true or false
    */
   public boolean hasTechnique();
   
   /**
    * Start the system initialization.
    */
   public void initPriorJ();

   /**
    * The number of selected techniques.
    * @return
    */
   public int getNumberOfTechniques();
  
   /**
    * This method confirm prioritization process done.
    */
   public void setIsPrioritized();

   /**
    * This method get the coverage log trace.
    * 
    * @return
    * 	A string with the coverage log trace.
    */
   public String getCodeTree();
   
   /**
    * This method get the prioritized position to a test case.
    * 
    * @param technique
    */
   public int getPrioritizedTestPositionByTechnique(String localPath, String testCaseName, TechniquesEnum technique);
   
   /**
    * This method calculate the F-measure to a given technique.
    * 
    * @param techniqueName
    * @return
    */
   public int fMeasure(String techniqueName); 
   
}