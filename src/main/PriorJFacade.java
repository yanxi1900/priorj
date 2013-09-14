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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import exception.*;

import java.awt.ScrollPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.JUnitVersionEnum;
import project.PriorJProject;

import technique.RefactoringEnum;
import technique.TechniquesEnum;
import util.FileManager;
import util.PathTo;

import controller.PriorJController;
import controller.ProjectController;
import controller.RBAController;


/**
 * This is is a facade to connect GUI with controller.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJFacade {

	private ProjectController controllerProject;
	private PriorJController controllerPriorj;
	private RBAController controllerRBA;
		
	public PriorJFacade(){
		controllerProject = new ProjectController();
		controllerPriorj = new PriorJController();
		controllerRBA = new RBAController();
	}
	
	/**
	 * Create a new Project with name and JUnit version.
	 * 
	 * @param name
	 * 		The project name.
	 * @param version
	 * 		The project version, example: JUnit3 or JUnit4
	 * @throws Exception 
	 */
	public void createProject(String name, String version) throws Exception{
    	
    	if (name == null)
    		throw new Exception("Invalid input");
      	
    	else if (name.isEmpty())
    		throw new EmptyPriorJProjectNameException();
    	
    	else if (version == null)
    		throw new Exception("Invalid input");
    	
    	else if (version.isEmpty())
    		throw new Exception("Empty version name");
    	
    	else if (!version.equals("junit3") && !version.equals("junit4"))
    		throw new Exception("Invalid junit version name");
    	
    	if (hasOpenedProject()){
    		closeProject();
    	}
   	    
		controllerProject.createNewProject(name, version);	
	}
	/**
	 * Say if a project with informed name exist.
	 * 
	 * @param projectName
	 * 		The project name.
	 * 
	 * @return
	 * 		True or false
	 * @throws Exception 
	 */
	public boolean searchProject(String projectName) throws Exception{
		if (projectName == null)
			throw new Exception("Invalid Project Name");
		
		if (projectName.isEmpty())
			throw new Exception("Empty Project Name");
		
		return controllerProject.searchProject(projectName);
	}
	
	/**
	 * Remove a project by name.
	 * 
	 * @param projectName
	 * 		the project name.
	 */
	public void removeProject(String projectName){
		controllerProject.removeProject(projectName);
	}
	
	/**
	 * This method save to priorj project directory all information about project opened.
	 * 
	 */
	public void commitProject() throws Exception{
            
            setProjectPathToOpenProject();
            if (!isSubversion())
                controllerProject.commitProject();
            else
                controllerProject.commitVersion();
        
	}
        
        
	/**
	 * This method open a project.
	 * 
	 * @param name
	 * 		The project name.
	 * @param version
	 * 		The project version
	 */
	public void openProject(String name, String version){
		controllerProject.openProject(name, version);
                
        String paths[] = controllerProject.getPathsOpenProject();
        
        try {
            setPathApp(paths[0]);
            setPathCode(paths[1]);
            setPathLib(paths[2]);    
            setPathTest(paths[3]);
        } catch (Exception ex) {
            System.err.println("error open Project: " + ex);
        }
                
    }
	
	/**
	 * Say if the project opened is a subversion.
	 * 
	 * @return
	 * 		true or false.
	 */
	public boolean isSubversion(){
		return controllerProject.isSubVersion();
	}
        
    /**
     * This method set which a project is a subversion.
     * 
     */
    public void setSubversion(){
        controllerProject.setSubVersion(true);
    }

    /**
     * This method say if a priorj project version is modificated.
     * 
     * @return 
     *      true or false.
     */
    public boolean isChanged(){
        return controllerPriorj.isChanged();
    }
    
    /**
     * This method set the JUnit version used by test cases.
     * 
     * @param version
     *      A String with junit3 or junit4
     */
    public void setJUnitVersion(String version) {
    	
    	if (!version.toLowerCase().equals("junit3") && !version.toLowerCase().equals("junit4"))
            throw new IllegalArgumentException("Invalid  JUnit version!");
        
        if (version.toLowerCase().equals("junit4")){
            controllerPriorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
        }
        else {
            controllerPriorj.setJUnitVersion(JUnitVersionEnum.JUNIT3);
        }
        
    }
    /**
     * The the JUnit version used in the project.
     * 
     * @return
     * 		"", junit3, or juni4.
     */
    public String getJUnitVersion(){
    	JUnitVersionEnum version = controllerPriorj.getJUnitVersion();
    	
    	String strVersion;
    	
    	if (version.equals(JUnitVersionEnum.JUNIT3))
    		strVersion = "junit3";
    	else
    		strVersion = "junit4";
       		
    	return strVersion;
    }
        
	/**
	 * Say if exist a opened project.
	 * 
	 * @return
	 * 		true or false.
	 */
	public boolean hasOpenedProject(){
		return controllerProject.hasOpenProject();
	}
	
	/**
	 * Run the coverage report, this method do instrumentation, coverage and
	 * read the log file generated.
	 * 
	 * @param pathApp
	 * 		the path application.
	 * @param pathCode
	 * 		the path code
	 * @param pathLib
	 * 		the path libraries.
	 * @param pathTest
	 * 		the path test.
	 */
	public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest){
		controllerPriorj.runTestCoverageReport(pathApp, pathCode, pathLib, pathTest);
	}
	
	/**
	 * Running the prioritization with the technique Total Method Coverage TMC.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationTMC(){
		try {
			return controllerPriorj.runPrioritizationTMC();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Running the prioritization with the technique Total Statement Coverage TSC.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationTSC(){
		try {
			return controllerPriorj.runPrioritizationTSC();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Running the prioritization with the technique Additional Statement Coverage ASC.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationASC(){
		try {
			return controllerPriorj.runPrioritizationTSC();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Running the prioritization with the technique Additional Method Coverage ASC.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationAMC(){
		try {
			return controllerPriorj.runPrioritizationAMC();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Running the prioritization with the technique Random RND.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationRND(){
		try {
			return controllerPriorj.runPrioritizationRND();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Running the prioritization with the Technique Total Echelon 
	 * (Changed Blocks CB).
	 * 
	 * @param pathCodeNew
	 * 		The path to a new version 
	 * @param pathCodeOld
	 * 		The path to a old version
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
	public List<String> runPrioritizationCB(String pathCodeNew, String pathCodeOld){
		try {
			return controllerPriorj.runPrioritizationCB(pathCodeNew, pathCodeOld);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
        
        /**
         * Running the instrumentation.
         * 
         * @throws InstrumentationUnrealizedException
         *      Files not found
         */
        public void runInstrumentation() throws InstrumentationUnrealizedException{
            controllerPriorj.runInstrumentation();
        }
        
        /**
         * Running the coverage.
         * 
         * @throws CoverageUnrealizedException 
         *      AspectJ errors
         */
        public void runCoverage() throws CoverageUnrealizedException, InstrumentationUnrealizedException{
            controllerPriorj.runCoverage();
        }
        /**
         * Running the read log file.
         * 
         * @throws CannotReadLogFileException
         *      Log file not generated.
         */
        public void runReadLog() throws CannotReadLogFileException, InstrumentationUnrealizedException, CoverageUnrealizedException{
            controllerPriorj.runReadLog();
        }
        
        /**
         * Running the prioritization.
         * 
         * @throws Exception 
         *      Suite not generated
         */
        public void runPrioritization() throws Exception {
            controllerPriorj.runPrioritization();
            controllerProject.setProjectPaths(getPathApp(), getPathCode(),
                    getPathLib(),getPathTest(), getPathCodeNew());
        }
        
        /**
         * This method is used to show a list of all projects.
         * 
         * @return
         *      A list of projects.
         */
        public List<PriorJProject> getProjects(){
            return controllerProject.getProjects();
        }
        
        /**
         * This method add a new technique to PriorJ.
         * 
         * 1. "TMC" - Total Method Coverage
         * 2. "TSC" - Total Statement Coverage
         * 3. ...
         * 7. "RBA" - Refactoring Based Approach
         * 
         * @param techniqueName 
         *      The technique name
         */
        public void addTechnique(String techniqueName){
            controllerPriorj.addTechnique(techniqueName);
        }
        /**
         * Remove a technique with informed name.
         * 
         * @param techniqueName
         *      The technique name.
         */
        public void removeTechnique(String techniqueName){
            controllerPriorj.removeTechnique(techniqueName);
        }
        /**
         * This method save a single prioritized suite test.
         * 
         * @param tests
         * @param technique 
         */
        public void saveJavaPrioritizedSuite(List<String> tests, String technique){
		
		if (technique.equals("TMC"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.TOTAL_METHOD_COVERAGE);
		else if (technique.equals("TSC"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.TOTAL_STATEMENT_COVERAGE);
		else if (technique.equals("ASC"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE);
		else if (technique.equals("AMC"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.ADDITIONAL_METHOD_COVERAGE);
		else if (technique.equals("CB"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.CHANGED_BLOCKS_TOTAL);
		else if (technique.equals("RND"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.Random);
		else if (technique.equals("RBA"))
			controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.REFACTORING_BASED_APPROACH);
		
	}
        
         /**
     * This method generate a selection in the prioritized suite code.
     * 
     * @param suiteName
     * 		the suite name given by user.
     * @param packageName
     * 		the package name given by user.
     * @param size
     * 		the percentage of selection size.
     * @return
     * 		the suite selection.
     */
    public List<String> generateSuiteSelection(String suiteName, String packageName, int size){
    	 String path = PathTo.ORDER + PathTo.SEPARATOR;
    	
    	 List<List<String>> suites = controllerProject.openAllPrioritizedTestSuites(path); 
    	 
    	 List<String> suiteNames = controllerProject.openSuitesNames();
    	 
    	 List<String> suiteSelection = controllerPriorj.generateSuiteSelection(suiteName, suites, suiteNames, packageName, size);
    	 
    	 return suiteSelection;
    }
    /**
     * This method save a suite selection.
     * 
     * @param suitesCode
     * 		The suite selection list.
     * @param path
     * 		The path  to local where is saved.
     * @param userSuiteName
     * 		the suite name given by user.
     */
    public void saveSuiteSelection(List<String> suiteCode, String path, String userSuiteName){
    	
    	List<String> suiteNames = controllerProject.openSuitesNames();
    	
    	controllerPriorj.saveSuiteSelection(path, suiteCode, suiteNames, userSuiteName);
    	
    }
    
        
        /**
	 * This method open all prioritized order into file.
	 *  
	 * @return
	 * 		A list of list with tests names.
	 */
	 public List<List<String>> openAllPrioritizedTestSuites(){
		String path = PathTo.ORDER + PathTo.SEPARATOR;
		
		return controllerProject.openAllPrioritizedTestSuites(path);
	}
        
    /**
     * This get the simple coverage Report.
     * 
     * @return
     *      A string.
     */
    public String getCoverageReport(){
        return controllerPriorj.getSimpleCoverageReport();
    }
    
    public List<String> getOrder(){
        return controllerProject.openOrder();
    }
    
    private void setProjectPathToOpenProject() throws Exception{
        controllerProject.setProjectPaths(getPathApp(), getPathCode(), getPathLib(), getPathTest(),getPathCodeNew());
        
    }
    
    public List<String> getTechniquesNames(){
        return controllerProject.openTechniquesNames();
    }
    
    /**
     * The Tree log execution.
     * 
     * @return
     *      A Jtree inside a scrollpane.
     */
    public ScrollPane getPanelTree(){
        return controllerPriorj.getTestCoverageTree().getTreePanel();
    }
    
    /**
     * This method get the code tree log trace.
     * 
     * @return
     * 		A string with code tree trace.
     */
    public String getCodeTree(){
    	return controllerPriorj.getCodeTree();
    }
    
    /**
     * Open all java prioritized suites, to last prioritization.
     * 
     * @return
     *      A list of suite codes.
     */
    public List<String> getJavaPrioritizedSuites(){
        return controllerProject.openJavaPrioritizedSuites();
    }
    
    /**
     * Get the all suite names.
     * 
     * @return 
     *      A list of string.
     */
    public List<String> getSuitesNames(){
        return controllerProject.openSuitesNames();
    }
    
    public String getSimpleJUnitReport(){
        return controllerPriorj.getSimpleJUnitReport();
    }
    
    public List<String> getFailedTests(){
        return controllerPriorj.getFailedTests();
    }
    
    public double generateAPFD(List<List<String>> combination){
        return controllerPriorj.generateAPFD(combination);
    }
    
    public JPanel generateAPFDChart(){
        return controllerPriorj.generateAPFDChart();
    }
    
    public void setPathApp(String pathApp) throws Exception {
    	if (pathApp ==null)
    		throw new Exception("Invalid application path Null");
    	
    	if (pathApp.isEmpty())
    		throw new Exception("Empty application path");
    	
    	pathApp = parser(pathApp);
 
    	if (!controllerPriorj.exist(pathApp))
    		throw new Exception("Application path not found");
    	
    	
        controllerPriorj.setPathApp(pathApp);
    }
    
    public String getPathApp(){
        return controllerPriorj.getPathApp();
    }
    
    public void setPathCode(String pathCode) throws Exception{
    	if (pathCode == null)
    		throw new Exception("Invalid code path Null");
    	
    	if (pathCode.isEmpty())
    		throw new Exception("Empty code path");
    	
    	pathCode = parser(pathCode);
    	
    	if (!controllerPriorj.exist(pathCode))
    		throw new Exception("Code path not found");
    	
        controllerPriorj.setPathCode(pathCode);
    }
    
    public String getPathCode(){
        return controllerPriorj.getPathCode();
    }
    
    public void setPathLib(String pathLib) throws Exception{
    	
    	if (pathLib == null)
    		throw new Exception("Invalid library path Null");
    	
    	pathLib = parser(pathLib);
    	
    	if (!pathLib.isEmpty() && !controllerPriorj.exist(pathLib)){
    		throw new Exception("Library path not found");
    	}
    	
        controllerPriorj.setPathLib(pathLib);
    }
    
    public String getPathLib(){
        return controllerPriorj.getPathLib();
    }
    
    public void setPathTest(String pathTest) throws Exception {
    	
    	if (pathTest == null)
    		throw new Exception("Invalid test path Null");
    	
    	if (pathTest.isEmpty())
    		throw new Exception("Empty test path");
    	
    	pathTest = parser(pathTest);
   	 
        if (!controllerPriorj.exist(pathTest))
        	throw new Exception("Test path not found");
               
        controllerPriorj.setPathTests(pathTest);
 
        }
        
        public String getPathTest(){
            return controllerPriorj.getPathTests();
        }
        
        public void setPathCodeNew(String pathCodeNew){
        	//do test and exceptions
            controllerPriorj.setPathNew(pathCodeNew);
        }
        
        public String getPathCodeNew(){
            return controllerPriorj.getPathNew();
        }
        
        public boolean isInstrumented(){
            return controllerPriorj.isInstrumented();
        }
        
        
     /**
      * This method say if this project is covered.
      * 
      * @return
      *  true or false.
      */
     public boolean isCovered(){
         return controllerPriorj.isCovered();
     }
          
     /**
      * This method say if this project has a log file.
      * 
      * @return
      *  true or false.
      */
     public boolean isReadLog(){
         return controllerPriorj.isInstrumented();
     }
     
     /**
      * This method say if this project is prioritized.
      * 
      * @return
      *  true or false.
      */
     public boolean isPrioritized(){
         return controllerPriorj.isInstrumented();
     }
     
     
        
    public void closeProject(){
        controllerProject.closeProject();
    }
    
    public void saveProject(){
        controllerProject.saveProject();
    }
            
        
	/**
	 * This method run the RBA with Renamed Method.
	 * 
	 * @param pathApp
	 * 		Path application.
	 * @param className
	 * 		The class name.
	 * @param methodName
	 * 		The method name.
	 * @param newMethodName
	 * 		The new method name.
	 * @return
	 */
	public List<String> runRBARenameMethod(String pathApp, String className, String methodName, String newMethodName){
		
        controllerRBA.setPathApp(pathApp);
        controllerRBA.setClassName(className);
        controllerRBA.setMethodName(methodName);
        controllerRBA.setNewMethodName(newMethodName);
        controllerRBA.run(RefactoringEnum.RENAME_METHOD);
        
        List<String> methodNames = controllerRBA.getMethods();
        
        return methodNames;
	}
	
	/**
	 *  This method run the RBA with extract method.
	 *  
	 * @param pathApp
	 * 		The path application.
	 * @param originMethodName
	 * 		The original method name.
	 * @param className
	 * 		The class name.
	 * @param newMethodName
	 * 		The new method name.
	 * @param beginLine
	 * 		The begin line.
	 * @param endLine
	 * 		The end line.
	 * 
	 * @return
	 * 		A list of method impacted.
	 */
	public List<String> runRBAExtractMethod(String pathApp, String originMethodName, String className, String newMethodName, int beginLine, int endLine){
		
		controllerRBA.setPathApp(pathApp);
        controllerRBA.setOriginMethodName(originMethodName);
        controllerRBA.setClassName(className);

        controllerRBA.setNewMethodName(newMethodName);
        controllerRBA.setBeginLine(beginLine);
        controllerRBA.setEndLine(endLine);

        controllerRBA.run(RefactoringEnum.EXTRAT_METHOD);

        List<String> methodNames = controllerRBA.getMethods();

        List<String> signatures = controllerRBA.getSignatures();
        
        return signatures;
	}
	
	/**
	 * This method run the RBA with Move method.
	 * 
	 * @param pathApp
	 * 		The path application.
	 * @param classOneName
	 * 		The class one name.
	 * @param classTwoName
	 * 		The class two name.
	 * @param methodName
	 * 		The method name.
	 * @return
	 * 		A list of strings.
	 */
	public List<String> runRBAMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName){
		
        controllerRBA.setPathApp(pathApp);
        controllerRBA.setClassOneName(classOneName);
        controllerRBA.setClassTwoName(classTwoName);
        controllerRBA.setMethodName(methodName);

        controllerRBA.run(RefactoringEnum.MOVE_METHOD);
        
        List<String> methodNames = controllerRBA.getMethods();
        
        return methodNames;
	}
	
	/**
	 * This method run the RBA with Pull Up Method.
	 * 
	 * @param pathApp
	 * 		The path application
	 * @param classOneName
	 * 		The class one name
	 * @param classTwoName
	 * 		The class two name
	 * @param methodName
	 * 		The method name
	 * @return
	 * 		A list of String
	 */
	public List<String> runRBAPullUpMethod(String pathApp, String classOneName, String classTwoName, String methodName){
		
        controllerRBA.setPathApp(pathApp);
        controllerRBA.setClassOneName(classOneName);
        controllerRBA.setClassTwoName(classTwoName);
        controllerRBA.setMethodName(methodName);

        controllerRBA.run(RefactoringEnum.PULL_UP_METHOD);

        List<String> methodNames = controllerRBA.getMethods();
        List<String> signatures = controllerRBA.getSignatures();

        return signatures;
    }
	
	/**
	 * This method run the RBA with Pull Up Field.
	 * 
	 * @param pathApp
	 * 		The path application
	 * @param classOneName
	 * 		The class one name.
	 * @param classTwoName
	 * 		The class two name
	 * @param fieldName
	 * 		The field name
	 * @return
	 * 		A list of string
	 */
	public List<String> runRBAPullUpField(String pathApp, String classOneName,String classTwoName, String fieldName){
		
        controllerRBA.setPathApp(pathApp);
        controllerRBA.setClassOneName(classOneName);
        controllerRBA.setClassTwoName(classTwoName);
        controllerRBA.setFieldName(fieldName);

        controllerRBA.run(RefactoringEnum.PULL_UP_FIELD);
        
        List<String> methodNames = controllerRBA.getMethods();
        
        List<String> signatures = controllerRBA.getSignatures();

        return signatures;
    }
	
	/**
	 * This method run the RBA with Add Parameter.
	 * 
	 * @param pathApp
	 * 		The path application
	 * @param className
	 * 		The class name
	 * @param methodName
	 * 		The method name.
	 * @return
	 * 		A list of string.
	 */
	 public List<String> runRBAAddParameter(String pathApp, String className, String methodName){
	        
	        controllerRBA.setPathApp(pathApp);
	        controllerRBA.setClassName(className);
	        controllerRBA.setMethodName(methodName);
	        controllerRBA.run(RefactoringEnum.ADD_PARAMETER);

	        List<String> methodNames = controllerRBA.getMethods();
	        
	        List<String> signatures = controllerRBA.getSignatures();
	        
	        return signatures;
	 }
    
    /**
	 * This method do a replace in the "/" or "\\" to
	 * <code>System.getProperty("file.separator")</code>
	 * 
	 * @param path
	 *            A path string, \home\doc\mypriorj
	 * @return A path changed.
	 */
	public String parser(String path) {

		if (path.contains("\\")) {
			path = path.replace("\\", PathTo.SEPARATOR);
		} else if (path.contains("/"))
			path = path.replace("/", PathTo.SEPARATOR);

		return path;
	}

	/**
	 * Remove all projects in the folder.
	 * @throws Exception 
	 */
	public void removeProjectAll() {
		controllerProject.removeAllProjects();
	}
	
	/**
	 * The 
	 * @return
	 */
	public int numberOfProjects(){
		return getProjects().size();
	}

	/**
	 * This method return the field value to indicated field from 
	 * opened project.
	 * 
	 * @param attribute
	 * @throws Exception 
	 */
	public String getAttributesOpenedProject(String attribute) throws Exception {
		
		if (attribute == null)
			throw new Exception("Invalid Attribute Null");
		
		if (attribute.isEmpty())
			throw new Exception("Empty Project Attribute");
		
		PriorJProject p = controllerProject.getOpenProject();
		
		if (attribute.equalsIgnoreCase("name")){
			return p.getName();
		}
		else if (attribute.equalsIgnoreCase("version")){
			return p.getVersion().getName();
		}
		else if (attribute.equalsIgnoreCase("pathApp")){
			return p.getPathApp();
		}
		else if (attribute.equalsIgnoreCase("pathCode")){
			return p.getPathCode();
		}
		else if (attribute.equalsIgnoreCase("pathLib")){
			return p.getPathLib();
		}
		else if (attribute.equalsIgnoreCase("pathTest")){
			return p.getPathTest();
		}
		else if (attribute.equalsIgnoreCase("pathNew")){
			return p.getPathCodeNew();
		}
		else if (attribute.equalsIgnoreCase("date")){
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			return formatDate.format(p.getDate().getTime());
		}
		else {
			throw new Exception ("Nonexistent Project Attribute");
		}
	}
	 
}
