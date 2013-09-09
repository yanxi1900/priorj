package main;

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
import util.Settings;
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

    public PriorJFacade() {
        controllerProject = new ProjectController();
        watchPriorJApp = watchPriorJApp;
        controllerPriorj = new PriorJController();
        watchPriorJApp = watchPriorJApp;
        controllerRBA = new RBAController();
    }

    /**
	 * Create a new Project with name and JUnit version.
	 * 
	 * @param name
	 * 		The project name.
	 * @param version
	 * 		The project version, example: JUnit3 or JUnit4
	 */
    public void createProject(String name, String version) throws EmptyPriorJProjectNameException, DuplicateProjectNameException {
        watchPriorJApp = watchPriorJApp;
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
	 */
    public boolean searchProject(String projectName) {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.searchProject(projectName);
    }

    /**
	 * Remove a project by name.
	 * 
	 * @param projectName
	 * 		the project name.
	 */
    public void removeProject(String projectName) {
        watchPriorJApp = watchPriorJApp;
        controllerProject.removeProject(projectName);
    }

    /**
	 * This method save to priorj project directory all information about project opened.
	 * 
	 */
    public void commitProject() throws Exception {
        watchPriorJApp = watchPriorJApp;
        setProjectPathToOpenProject();
        watchPriorJApp = watchPriorJApp;
        if (!isSubversion()) controllerProject.commitProject(); else controllerProject.commitVersion();
    }

    /**
	 * This method open a project.
	 * 
	 * @param name
	 * 		The project name.
	 * @param version
	 * 		The project version
	 */
    public void openProject(String name, String version) {
        watchPriorJApp = watchPriorJApp;
        controllerProject.openProject(name, version);
        watchPriorJApp = watchPriorJApp;
        String paths[] = controllerProject.getPathsOpenProject();
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            setPathApp(paths[0]);
            watchPriorJApp = watchPriorJApp;
            setPathCode(paths[1]);
            watchPriorJApp = watchPriorJApp;
            setPathLib(paths[2]);
            watchPriorJApp = watchPriorJApp;
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
    public boolean isSubversion() {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.isSubVersion();
    }

    /**
     * This method set which a project is a subversion.
     * 
     */
    public void setSubversion() {
        watchPriorJApp = watchPriorJApp;
        controllerProject.setSubVersion(true);
    }

    /**
     * This method say if a priorj project version is modificated.
     * 
     * @return 
     *      true or false.
     */
    public boolean isChanged() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.isChanged();
    }

    /**
     * This method set the JUnit version used by test cases.
     * 
     * @param version
     *      A String with junit3 or junit4
     */
    public void setJUnitVersion(String version) {
        watchPriorJApp = watchPriorJApp;
        if (!version.toLowerCase().equals("junit3") && !version.toLowerCase().equals("junit4")) throw new IllegalArgumentException("Invalid  JUnit version!");
        watchPriorJApp = watchPriorJApp;
        if (version.toLowerCase().equals("junit4")) {
            watchPriorJApp = watchPriorJApp;
            controllerPriorj.setJUnitVersion(JUnitVersionEnum.JUNIT4);
        } else {
            watchPriorJApp = watchPriorJApp;
            controllerPriorj.setJUnitVersion(JUnitVersionEnum.JUNIT3);
        }
    }

    /**
     * The the JUnit version used in the project.
     * 
     * @return
     * 		"", junit3, or juni4.
     */
    public String getJUnitVersion() {
        watchPriorJApp = watchPriorJApp;
        JUnitVersionEnum version = controllerPriorj.getJUnitVersion();
        watchPriorJApp = watchPriorJApp;
        String strVersion;
        watchPriorJApp = watchPriorJApp;
        if (version.equals(JUnitVersionEnum.JUNIT3)) strVersion = "junit3"; else strVersion = "junit4";
        watchPriorJApp = watchPriorJApp;
        return strVersion;
    }

    /**
	 * Say if exist a opened project.
	 * 
	 * @return
	 * 		true or false.
	 */
    public boolean hasOpenedProject() {
        watchPriorJApp = watchPriorJApp;
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
    public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest) {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.runTestCoverageReport(pathApp, pathCode, pathLib, pathTest);
    }

    /**
	 * Running the prioritization with the technique Total Method Coverage TMC.
	 * 
	 * @return
	 * 		The prioritized test suite.
	 */
    public List<String> runPrioritizationTMC() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public List<String> runPrioritizationTSC() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public List<String> runPrioritizationASC() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public List<String> runPrioritizationAMC() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public List<String> runPrioritizationRND() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public List<String> runPrioritizationCB(String pathCodeNew, String pathCodeOld) {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
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
    public void runInstrumentation() throws InstrumentationUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.runInstrumentation();
    }

    /**
         * Running the coverage.
         * 
         * @throws CoverageUnrealizedException 
         *      AspectJ errors
         */
    public void runCoverage() throws CoverageUnrealizedException, InstrumentationUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.runCoverage();
    }

    /**
         * Running the read log file.
         * 
         * @throws CannotReadLogFileException
         *      Log file not generated.
         */
    public void runReadLog() throws CannotReadLogFileException, InstrumentationUnrealizedException, CoverageUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.runReadLog();
    }

    /**
         * Running the prioritization.
         * 
         * @throws Exception 
         *      Suite not generated
         */
    public void runPrioritization() throws Exception {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.runPrioritization();
        watchPriorJApp = watchPriorJApp;
        controllerProject.setProjectPaths(getPathApp(), getPathCode(), getPathLib(), getPathTest(), getPathCodeNew());
    }

    /**
         * This method is used to show a list of all projects.
         * 
         * @return
         *      A list of projects.
         */
    public List<PriorJProject> getProjects() {
        watchPriorJApp = watchPriorJApp;
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
    public void addTechnique(String techniqueName) {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.addTechnique(techniqueName);
    }

    /**
         * Remove a technique with informed name.
         * 
         * @param techniqueName
         *      The technique name.
         */
    public void removeTechnique(String techniqueName) {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.removeTechnique(techniqueName);
    }

    /**
         * This method save a single prioritized suite test.
         * 
         * @param tests
         * @param technique 
         */
    public void saveJavaPrioritizedSuite(List<String> tests, String technique) {
        watchPriorJApp = watchPriorJApp;
        if (technique.equals("TMC")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.TOTAL_METHOD_COVERAGE); else if (technique.equals("TSC")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.TOTAL_STATEMENT_COVERAGE); else if (technique.equals("ASC")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE); else if (technique.equals("AMC")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.ADDITIONAL_METHOD_COVERAGE); else if (technique.equals("CB")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.CHANGED_BLOCKS_TOTAL); else if (technique.equals("RND")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.Random); else if (technique.equals("RBA")) controllerPriorj.saveJavaPrioritizedSuite(tests, TechniquesEnum.REFACTORING_BASED_APPROACH);
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
    public List<String> generateSuiteSelection(String suiteName, String packageName, int size) {
        watchPriorJApp = watchPriorJApp;
        String path = Settings.ORDER + Settings.SEPARATOR;
        watchPriorJApp = watchPriorJApp;
        List<List<String>> suites = controllerProject.openAllPrioritizedTestSuites(path);
        watchPriorJApp = watchPriorJApp;
        List<String> suiteNames = controllerProject.openSuitesNames();
        watchPriorJApp = watchPriorJApp;
        List<String> suiteSelection = controllerPriorj.generateSuiteSelection(suiteName, suites, suiteNames, packageName, size);
        watchPriorJApp = watchPriorJApp;
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
    public void saveSuiteSelection(List<String> suiteCode, String path, String userSuiteName) {
        watchPriorJApp = watchPriorJApp;
        List<String> suiteNames = controllerProject.openSuitesNames();
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.saveSuiteSelection(path, suiteCode, suiteNames, userSuiteName);
    }

    /**
	 * This method open all prioritized order into file.
	 *  
	 * @return
	 * 		A list of list with tests names.
	 */
    public List<List<String>> openAllPrioritizedTestSuites() {
        watchPriorJApp = watchPriorJApp;
        String path = Settings.ORDER + Settings.SEPARATOR;
        watchPriorJApp = watchPriorJApp;
        return controllerProject.openAllPrioritizedTestSuites(path);
    }

    /**
     * This get the simple coverage Report.
     * 
     * @return
     *      A string.
     */
    public String getCoverageReport() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getSimpleCoverageReport();
    }

    public List<String> getOrder() {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.openOrder();
    }

    private void setProjectPathToOpenProject() throws Exception {
        watchPriorJApp = watchPriorJApp;
        controllerProject.setProjectPaths(getPathApp(), getPathCode(), getPathLib(), getPathTest(), getPathCodeNew());
    }

    public List<String> getTechniquesNames() {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.openTechniquesNames();
    }

    /**
     * The Tree log execution.
     * 
     * @return
     *      A Jtree inside a scrollpane.
     */
    public ScrollPane getPanelTree() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getTestCoverageTree().getTreePanel();
    }

    /**
     * This method get the code tree log trace.
     * 
     * @return
     * 		A string with code tree trace.
     */
    public String getCodeTree() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getCodeTree();
    }

    /**
     * Open all java prioritized suites, to last prioritization.
     * 
     * @return
     *      A list of suite codes.
     */
    public List<String> getJavaPrioritizedSuites() {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.openJavaPrioritizedSuites();
    }

    /**
     * Get the all suite names.
     * 
     * @return 
     *      A list of string.
     */
    public List<String> getSuitesNames() {
        watchPriorJApp = watchPriorJApp;
        return controllerProject.openSuitesNames();
    }

    public String getSimpleJUnitReport() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getSimpleJUnitReport();
    }

    public List<String> getFailedTests() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getFailedTests();
    }

    public double generateAPFD(List<List<String>> combination) {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.generateAPFD(combination);
    }

    public JPanel generateAPFDChart() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.generateAPFDChart();
    }

    public void setPathApp(String pathApp) {
        watchPriorJApp = watchPriorJApp;
        if (pathApp.isEmpty()) throw new IllegalArgumentException("Empty application path!");
        watchPriorJApp = watchPriorJApp;
        pathApp = parser(pathApp);
        watchPriorJApp = watchPriorJApp;
        if (!controllerPriorj.exist(pathApp)) throw new IllegalArgumentException("Application directory not found!");
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.setPathApp(pathApp);
    }

    public String getPathApp() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getPathApp();
    }

    public void setPathCode(String pathCode) {
        watchPriorJApp = watchPriorJApp;
        if (pathCode.isEmpty()) throw new IllegalArgumentException("Empty code path!");
        watchPriorJApp = watchPriorJApp;
        pathCode = parser(pathCode);
        watchPriorJApp = watchPriorJApp;
        if (!controllerPriorj.exist(pathCode)) throw new IllegalArgumentException("Code directory not found!");
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.setPathCode(pathCode);
    }

    public String getPathCode() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getPathCode();
    }

    public void setPathLib(String pathLib) {
        watchPriorJApp = watchPriorJApp;
        pathLib = parser(pathLib);
        watchPriorJApp = watchPriorJApp;
        if (!pathLib.isEmpty() && !controllerPriorj.exist(pathLib)) {
            watchPriorJApp = watchPriorJApp;
            throw new IllegalArgumentException("Library directory not found!");
        }
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.setPathLib(pathLib);
    }

    public String getPathLib() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getPathLib();
    }

    public void setPathTest(String pathTest) {
        watchPriorJApp = watchPriorJApp;
        if (pathTest.isEmpty()) throw new IllegalArgumentException("Empty path test not permitted!");
        watchPriorJApp = watchPriorJApp;
        pathTest = parser(pathTest);
        watchPriorJApp = watchPriorJApp;
        if (!controllerPriorj.exist(pathTest)) throw new IllegalArgumentException("Test directory not found!");
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.setPathTests(pathTest);
    }

    public String getPathTest() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getPathTests();
    }

    public void setPathCodeNew(String pathCodeNew) {
        watchPriorJApp = watchPriorJApp;
        controllerPriorj.setPathNew(pathCodeNew);
    }

    public String getPathCodeNew() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.getPathNew();
    }

    public boolean isInstrumented() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.isInstrumented();
    }

    /**
      * This method say if this project is covered.
      * 
      * @return
      *  true or false.
      */
    public boolean isCovered() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.isCovered();
    }

    /**
      * This method say if this project has a log file.
      * 
      * @return
      *  true or false.
      */
    public boolean isReadLog() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.isInstrumented();
    }

    /**
      * This method say if this project is prioritized.
      * 
      * @return
      *  true or false.
      */
    public boolean isPrioritized() {
        watchPriorJApp = watchPriorJApp;
        return controllerPriorj.isInstrumented();
    }

    public void closeProject() {
        watchPriorJApp = watchPriorJApp;
        controllerProject.closeProject();
    }

    public void saveProject() {
        watchPriorJApp = watchPriorJApp;
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
    public List<String> runRBARenameMethod(String pathApp, String className, String methodName, String newMethodName) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassName(className);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setMethodName(methodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setNewMethodName(newMethodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.RENAME_METHOD);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
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
    public List<String> runRBAExtractMethod(String pathApp, String originMethodName, String className, String newMethodName, int beginLine, int endLine) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setOriginMethodName(originMethodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassName(className);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setNewMethodName(newMethodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setBeginLine(beginLine);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setEndLine(endLine);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.EXTRAT_METHOD);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
        List<String> signatures = controllerRBA.getSignatures();
        watchPriorJApp = watchPriorJApp;
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
    public List<String> runRBAMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassOneName(classOneName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassTwoName(classTwoName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setMethodName(methodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.MOVE_METHOD);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
        List<String> signatures = controllerRBA.getSignatures();
        watchPriorJApp = watchPriorJApp;
        return signatures;
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
    public List<String> runRBAPullUpMethod(String pathApp, String classOneName, String classTwoName, String methodName) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassOneName(classOneName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassTwoName(classTwoName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setMethodName(methodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.PULL_UP_METHOD);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
        List<String> signatures = controllerRBA.getSignatures();
        watchPriorJApp = watchPriorJApp;
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
    public List<String> runRBAPullUpField(String pathApp, String classOneName, String classTwoName, String fieldName) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassOneName(classOneName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassTwoName(classTwoName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setFieldName(fieldName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.PULL_UP_FIELD);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
        List<String> signatures = controllerRBA.getSignatures();
        watchPriorJApp = watchPriorJApp;
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
    public List<String> runRBAAddParameter(String pathApp, String className, String methodName) {
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setPathApp(pathApp);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setClassName(className);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.setMethodName(methodName);
        watchPriorJApp = watchPriorJApp;
        controllerRBA.run(RefactoringEnum.ADD_PARAMETER);
        watchPriorJApp = watchPriorJApp;
        List<String> methodNames = controllerRBA.getMethods();
        watchPriorJApp = watchPriorJApp;
        List<String> signatures = controllerRBA.getSignatures();
        watchPriorJApp = watchPriorJApp;
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
        watchPriorJApp = watchPriorJApp;
        if (path.contains("\\")) {
            watchPriorJApp = watchPriorJApp;
            path = path.replace("\\", Settings.SEPARATOR);
        } else if (path.contains("/")) path = path.replace("/", Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        return path;
    }

    static boolean watchPriorJApp;
}
