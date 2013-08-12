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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import prioritization.Prioritization;
import project.JUnitVersionEnum;
import report.CodeTree;
import report.CoverageReport;
import report.GenerateCoverageReport;
import report.GenerateExecutionOrderReport;
import report.GenerateTestSuite;
import report.GenerateTestSuiteForJUnit4;
import report.JUnitReport;
import report.SuiteFactory;
import system.PriorJSystem;
import system.PriorJSystemImpl;
import technique.RefactoringEnum;
import technique.TechniquesEnum;
import ui.TreeBuilder;
import util.ManagerFiles;
import util.ReadFile;
import util.ReadXML;
import util.SaveFile;
import util.Settings;
import util.TestResult;

import controller.RBAController;
import coverage.TestCase;
import coverage.TestSuite;
import coverage.ClassCode;

import apfd.GenerateAPFD;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.DirectoryNotExistException;
import exception.EmptyPathException;
import exception.EmptySetOfTestCaseException;
import exception.InstrumentationUnrealizedException;

/**
 * This class implement the main functionalities of interface PriorJ.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJImpl implements PriorJ {

	private PriorJSystem system;
	private Prioritization prior;
	private JUnitVersionEnum version;
	private int suiteSelectionSize;

	private List<String> blockAffected;

	private String pathApp = "", pathCode = "", pathLib = "", pathTest = "", pathCodeNew = "";

	private List<TechniquesEnum> techniques;

	private boolean isPrioritized, isInit;
	
	/**
	 * Constant to indicate test case not found!
	 */
	private final int TEST_NOT_FOUND = -1;

	/**
	 * Default Constructor.
	 */
	public PriorJImpl() {
		
		system = new PriorJSystemImpl();
		
		version = JUnitVersionEnum.JUNIT3;

		suiteSelectionSize = 100;

		techniques = new LinkedList<TechniquesEnum>();

		initialState();
	}

	/**
	 * Set the state initial to process.
	 * 
	 */
	private void initialState() {
		isPrioritized = false;
		isInit = false;
	}

	public void initPriorJ() {
		
		pathCode = pathCode.replace(pathApp + Settings.SEPARATOR, "");
		pathLib = pathLib.replace(pathApp + Settings.SEPARATOR, "");
		pathTest = pathTest.replace(pathApp + Settings.SEPARATOR, "");
				
		system = new PriorJSystemImpl(pathApp, pathCode, pathTest, pathCodeNew, pathLib);
		
		setInitialized();
	}

	public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest) {
		
		setPathApplication(pathApp);
		setPathCode(pathCode);
		setPathLibraries(pathLib);
		setPathTests(pathTest);

		initPriorJ();
		
		try {
			
			runInstrumentation();
			
			runCoverage();
			
			runReadLog();
		} catch (InstrumentationUnrealizedException e) {
			System.err.println(e.getMessage());
			
		} catch(CoverageUnrealizedException e){
			System.err.println(e.getMessage());
	
		} catch(CannotReadLogFileException e){
			System.err.println(e.getMessage());
		}
	
	}

	public void runInstrumentation() throws InstrumentationUnrealizedException {
		if (!isInitialized())
			initPriorJ();
		
		system.runInstrumentation();
		updatePaths();
	}

	public void runCoverage() throws CoverageUnrealizedException {
		system.runCoverage(version);
		
	}

	public void runReadLog() throws CannotReadLogFileException {
		system.readLogFile();

		// save the coverage report
		String reportCoverage = getSimplifiedCoverageReport(getCoverageReport());
	}
	
	private void updatePaths(){
		this.pathApp = system.getPathApp();
		this.pathCode = system.getPathCode();
		this.pathLib = system.getPathLib();
		this.pathTest = system.getPathTests();
		this.pathCodeNew = system.getPathCodeNew();
	}

	public List<TestCase> getTestCases() {
		return system.getCoverageReport().getTests();
	}

	public CoverageReport getCoverageReport() {
		return system.getCoverageReport();
	}

	public void runPrioritization() throws Exception {

		List<TestCase> tests = getCoverageReport().getTests();

		List<String> prioritizedTests = new ArrayList<String>();

		for (TechniquesEnum technique : this.techniques) {
			
			prioritizedTests = runPrioritization(tests, technique, getPathCodeNew(),getTotalPathCode());
			
			saveJavaPrioritizedSuite(prioritizedTests, technique);
			savePrioritizationOrder(prioritizedTests, technique);
		}

		isPrioritized = true;
	}

	/**
	 * This method is auxiliary to run multiples techniques.
	 * 
	 * @param tests
	 *            A list of Test Cases.
	 * @param technique
	 *            A Prioritization Technique.
	 * @return A list of prioritized tests.
	 */
	private List<String> runPrioritization(List<TestCase> tests, TechniquesEnum technique, String pathNewCode, String pathCodeOld) throws EmptySetOfTestCaseException {

		List<String> prioritizedTests = new ArrayList<String>();

		switch (technique.getId()) {
		case 1:
			prioritizedTests = runPrioritizationTSC(tests);
			break;
		case 2:
			prioritizedTests = runPrioritizationTMC(tests);
			break;
		case 3:
			prioritizedTests = runPrioritizationASC(tests);
			break;
		case 4:
			prioritizedTests = runPrioritizationAMC(tests);
			break;
		case 5:
			prioritizedTests = runPrioritizationCB(tests, pathNewCode ,pathCodeOld);
			break;
		case 6:
			prioritizedTests = runPrioritizationRD(tests);
			break;
		case 8:
			prioritizedTests = runPrioritizationRBA(tests);
			break;

		default:
			break;

		}
		isPrioritized = true;
		return prioritizedTests;
	}

	public List<String> runPrioritizationAMC(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		prior = new Prioritization(tests,
				TechniquesEnum.ADDITIONAL_METHOD_COVERAGE.getId(), "", "");
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationASC(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		prior = new Prioritization(tests,
				TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE.getId(), "", "");
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationTMC(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		prior = new Prioritization(tests,
				TechniquesEnum.TOTAL_METHOD_COVERAGE.getId(), "", "");
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationTSC(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		Prioritization prior = new Prioritization(tests,TechniquesEnum.TOTAL_STATEMENT_COVERAGE.getId(), "", "");
		
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationRD(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		prior = new Prioritization(tests, TechniquesEnum.Random.getId(), "", "");
		
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationCB(List<TestCase> tests, String pathNewCode, String pathCodeOld)
			throws EmptySetOfTestCaseException {

		Prioritization prior = new Prioritization(tests,TechniquesEnum.CHANGED_BLOCKS_TOTAL.getId(), pathNewCode,pathCodeOld);
		
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		this.blockAffected = prior.getBlockAffected();

		return prior.assignWeight();
	}

	public List<String> runPrioritizationRBA(List<TestCase> tests)
			throws EmptySetOfTestCaseException {
		Prioritization prior = new Prioritization(tests,
				TechniquesEnum.REFACTORING_BASED_APPROACH.getId(), "", "");
		
		prior.setPathTest(pathTest);
		
		prior.prioritize();

		return prior.assignWeight();
	}

	public String getSimplifiedCoverageReport(CoverageReport coverage) {

		GenerateCoverageReport report = new GenerateCoverageReport(
				coverage.getSuites());

		String simpleReport = report.genereteCoverageReport();

		SaveFile.saveCoverageReport(simpleReport);

		return simpleReport;
	}

	public CodeTree getTestCoverageTree(CoverageReport coverage) {
		TreeBuilder tree = new TreeBuilder();

		tree.buildTree(coverage.getSuites());

		CodeTree codetree = new CodeTree(tree);

		return codetree;
	}

	public CodeTree openTestCoverageTree() {
		TreeBuilder tree = new TreeBuilder();
		try {

			List<TestSuite> suites = ReadXML.getAllTestSuites();

			CoverageReport coverage = new CoverageReport(suites);

			tree.buildTree(coverage.getSuites());

			CodeTree codetree = new CodeTree(tree);

			return codetree;

		} catch (Exception ex) {
			System.err.println("Open Test Coverage Tree error: "
					+ ex.getMessage());
			return new CodeTree(new TreeBuilder());
		}

	}

	public String openPrioritizationOrder(TechniquesEnum technique) {

		String local = Settings.ORDER + Settings.SEPARATOR;
		String fileName = local + ManagerFiles.alias(technique.getId())+ ".txt";

		try {
			String content = ReadFile.read(fileName);

			return content;

		} catch (Exception e) {
			System.err
					.println("Open Prioritized Order error:" + e.getMessage());
			return "";
		}
	}

	public String openSimpleCoverageReport() {
		try {
			String filename = Settings.REPORT + Settings.SEPARATOR
					+ Settings.FILE_COVERAGE_REPORT;

			String report = ReadFile.read(filename);

			return report;
		} catch (Exception ex) {
			System.err.println("Coverage Report error: " + ex.getMessage());

			return "";
		}
	}

	public JUnitReport getJUnitReport() {

		JUnitReport report = new JUnitReport();

		return report;
	}

	public String simpleJUnitReport() {

		JUnitReport report = getJUnitReport();

		return report.toString();
	}

	public float calculateAPFD(List<String> tests,	List<List<String>> testCaseCombination) {

		GenerateAPFD apfdGenerate = new GenerateAPFD();

		apfdGenerate.setTests(tests);
		apfdGenerate.setTestsCombination(testCaseCombination);

		return apfdGenerate.calculateAPFD();
	}

	public void saveJavaPrioritizedSuite(List<String> tests,TechniquesEnum technique) {
		String code = "";

		String packageName;

		try {

			packageName = GenerateTestSuite.getPackageName(system.getTotalPathTests());
			
			if (version == JUnitVersionEnum.JUNIT3) {
				code = GenerateTestSuite.generate(packageName, tests,suiteSelectionSize);
			} else {
				code = GenerateTestSuiteForJUnit4.generate(packageName, tests,suiteSelectionSize);
			}

			SaveFile.saveCode(ManagerFiles.alias(technique.getId()), code);

		} catch (Exception e) {
			System.err.println("save Java Prioritized suite error: "+ e.getMessage());
		}

	}

	public void savePrioritizationOrder(List<String> prioritizedTests,
			TechniquesEnum technique) {

		GenerateExecutionOrderReport executionOrder = new GenerateExecutionOrderReport(
				prioritizedTests, technique.getId());

		String result = executionOrder.generateExecutionOrder();

		String techniqueNames = ManagerFiles.alias(technique.getId());

		SaveFile.saveFileOrderExecutionTestCase(techniqueNames, result);
	}

	public void setJUnitVersion(JUnitVersionEnum version) {
		this.version = version;
	}

	public List<String> getFailedTestCase() {
		List<String> tests = new LinkedList<String>();

		List<TestResult> resultList = getJUnitReport().getResult();

		for (TestResult result : resultList) {
			if (!result.isPassed()) {
				String testName = result.getNameSuite() + "."
						+ result.getTestName();
				tests.add(testName);
			}
		}

		return tests;
	}

	public void setPathApplication(String pathApplication) {
		this.pathApp = pathApplication;
	}

	public String getPathApplication() {
		return this.pathApp;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}

	public String getPathCode() {
		return this.pathCode;
	}

	public void setPathLibraries(String pathLibraries) {
		this.pathLib = pathLibraries;
	}

	public String getPathLibraries() {
		return this.pathLib;
	}

	public void setPathTests(String pathTests) {
		this.pathTest = pathTests;
	}

	public String getPathTests() {
		return this.pathTest;
	}

	public void setPathCodeNew(String pathCodeNew) {
		this.pathCodeNew = pathCodeNew;
	}
	
	public String getPathCodeNew() {
		return this.pathCodeNew;
	}


	public String getTotalPathCode(){
		return system.getTotalPathCode();
	}
	
	public String getTotalPathTest(){
		return system.getTotalPathTests();
	}
	
	public void SetPrioritizationTechniques(List<TechniquesEnum> technques) {
		this.techniques = techniques;
	}

	public void addPrioritizationTechnique(TechniquesEnum technique) {
		this.techniques.add(technique);
	}

	public void removePrioritizationTechnique(TechniquesEnum technique) {
		this.techniques.remove(technique);
	}

	public List<TechniquesEnum> getPrioritizationTechniques() {
		return techniques;
	}

	public List<String> blockAffected() {

		if (blockAffected == null) {
			try {
				system.instrumentCode(getPathCodeNew());

				blockAffected = system.checkDifference(getPathCodeNew(), getTotalPathCode());

				return blockAffected;
			} catch (Exception ex) {
				System.err.println("Block Affected: " + ex.getMessage());
				return new ArrayList<String>();
			}
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Override
	public List<String> runRenameMethod(String pathApp, String className, String methodName, String newMethodName) {
		// create the controller
		RBAController controller = new RBAController();

		// set values
		controller.setPathApp(pathApp);
		controller.setClassName(className);
		controller.setMethodName(methodName);
		controller.setNewMethodName(newMethodName);

		// run
		controller.run(RefactoringEnum.RENAME_METHOD);

		// get result.
		List<String> methodNames = controller.getMethods();

		//List<String> signatures = controller.getSignatures();

		return methodNames;
	}

	@Override
	public List<String> runExtractMethod(String pathApp, String originMethodName,
                   String className, String methodName, String newMethodName,
			        int beginLine, int endLine) {
		// create the controller
		RBAController controller = new RBAController();
		// set values
		controller.setPathApp(pathApp);
		controller.setOriginMethodName(originMethodName);
		controller.setClassName(className);
		controller.setMethodName(methodName);
		controller.setNewMethodName(newMethodName);
		controller.setBeginLine(beginLine);
		controller.setEndLine(endLine);

		controller.run(RefactoringEnum.EXTRAT_METHOD);

		List<String> methodNames = controller.getMethods();

		//List<String> signatures = controller.getSignatures();

		return methodNames;
	}

	@Override
	public List<String> runMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName) {
		// create the controller
		RBAController controller = new RBAController();
		// set values
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setMethodName(methodName);

		controller.run(RefactoringEnum.MOVE_METHOD);

		List<String> methodNames = controller.getMethods();

		List<String> signatures = controller.getSignatures();
		return signatures;
		//return methodNames;
	}

	public List<String> runAddParameter(String pathApp, String className, String methodName) {

		// create the controller
		RBAController controller = new RBAController();
		// set values
		controller.setPathApp(pathApp);
		controller.setClassName(className);
		controller.setMethodName(methodName);

		// run
		controller.run(RefactoringEnum.ADD_PARAMETER);

		// get result.
		List<String> methodNames = controller.getMethods();

		//List<String> signatures = controller.getSignatures();

		return methodNames;
	}

	public List<String> runPullUpField(String pathApp, String classOneName, String classTwoName, String fieldName) {
		// create the controller
		RBAController controller = new RBAController();
		// set values
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setFieldName(fieldName);

		// run
		controller.run(RefactoringEnum.PULL_UP_FIELD);

		// get result.
		List<String> methodNames = controller.getMethods();

		//List<String> signatures = controller.getSignatures();

		return methodNames;
	}

	public List<String> runPullUpMethod(String pathApp, String classOneName,
			String classTwoName, String methodName) {
		// create the controller
		RBAController controller = new RBAController();
		// set values
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setMethodName(methodName);

		controller.run(RefactoringEnum.PULL_UP_METHOD);

		List<String> methodNames = controller.getMethods();

		//List<String> signatures = controller.getSignatures();

		return methodNames;
	}

	
	public boolean isInstrumented() {
		return system.isInstrumented();
	}

	public boolean isCovered() {
		return system.isCovered();
	}

	public boolean isReadLog() {
		return system.isLog();
	}

	public boolean isPrioritized() {
		return isPrioritized;
	}
	
	public void setIsPrioritized(){
		isPrioritized = true;
	}
	
	/**
	 * This method say if the system is initialized.
	 * 
	 * @return
	 *  	true or false.
	 */
	private boolean isInitialized(){
		return isInit;
	}
	/**
	 * Turn the system initialized.
	 */
	private void setInitialized(){
		isInit =true;
	}

	public List<String> generateSuiteSelection(String suiteName, List<List<String>> suites, List<String> filenames,
			String packageName, int size) {
		
		SuiteFactory factory = new SuiteFactory(suiteName, packageName, size);
		
		List<String> suiteList;
		try {
			suiteList = factory.generateSuite(suites, filenames);

			return suiteList;
			
		} catch (Exception e) {
			System.err.println("generate suite selection error: " + e.getMessage());
			return new ArrayList<String>();
		}
		
	}

    public void saveSuiteSelection(String path, List<String> suiteCode, List<String> suiteNames, String suiteUserName) {
            SuiteFactory factory = new SuiteFactory();

            for (int i=0; i< suiteNames.size(); i++){
                    String name = suiteUserName + suiteNames.get(i);
                    suiteNames.set(i, name);
            }

            factory.save(path, suiteCode, suiteNames);
    }

    public boolean hasTechnique(){
    	return techniques.size() != 0;
    }

	@Override
	public int getNumberOfTechniques() {
		// TODO Auto-generated method stub
		return techniques.size();
	}

	@Override
	public String getCodeTree() {
		CoverageReport report = getCoverageReport();
		
		TreeBuilder tree = new TreeBuilder();
		tree.buildTree(report.getSuites());
		
		return tree.toString();
	}

	@Override
	public int getPrioritizedTestPositionByTechnique(String localPath, String testCaseName, TechniquesEnum technique) {
		
		String filename = ManagerFiles.alias(technique.getId()) + ".txt";
		String path = localPath + Settings.SEPARATOR + filename;
		
		List<String> testsList = ReadFile.readFileAndReturnList(path);
		
		if (testsList.contains(testCaseName))
			return testsList.indexOf(testCaseName) + 1;
		
		return TEST_NOT_FOUND;
	}

	@Override
	public int fMeasure(String techniqueName) {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
