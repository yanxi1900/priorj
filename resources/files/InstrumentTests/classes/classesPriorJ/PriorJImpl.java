package main;

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
        watchPriorJApp = watchPriorJApp;
        version = JUnitVersionEnum.JUNIT3;
        watchPriorJApp = watchPriorJApp;
        suiteSelectionSize = 100;
        watchPriorJApp = watchPriorJApp;
        techniques = new LinkedList<TechniquesEnum>();
        watchPriorJApp = watchPriorJApp;
        initialState();
    }

    /**
	 * Set the state initial to process.
	 * 
	 */
    private void initialState() {
        watchPriorJApp = watchPriorJApp;
        isPrioritized = false;
        watchPriorJApp = watchPriorJApp;
        isInit = false;
    }

    public void initPriorJ() {
        watchPriorJApp = watchPriorJApp;
        pathCode = pathCode.replace(pathApp + Settings.SEPARATOR, "");
        watchPriorJApp = watchPriorJApp;
        pathLib = pathLib.replace(pathApp + Settings.SEPARATOR, "");
        watchPriorJApp = watchPriorJApp;
        pathTest = pathTest.replace(pathApp + Settings.SEPARATOR, "");
        watchPriorJApp = watchPriorJApp;
        system = new PriorJSystemImpl(pathApp, pathCode, pathTest, pathCodeNew, pathLib);
        watchPriorJApp = watchPriorJApp;
        setInitialized();
    }

    public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest) {
        watchPriorJApp = watchPriorJApp;
        setPathApplication(pathApp);
        watchPriorJApp = watchPriorJApp;
        setPathCode(pathCode);
        watchPriorJApp = watchPriorJApp;
        setPathLibraries(pathLib);
        watchPriorJApp = watchPriorJApp;
        setPathTests(pathTest);
        watchPriorJApp = watchPriorJApp;
        initPriorJ();
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            runInstrumentation();
            watchPriorJApp = watchPriorJApp;
            runCoverage();
            watchPriorJApp = watchPriorJApp;
            runReadLog();
        } catch (InstrumentationUnrealizedException e) {
            System.err.println(e.getMessage());
        } catch (CoverageUnrealizedException e) {
            System.err.println(e.getMessage());
        } catch (CannotReadLogFileException e) {
            System.err.println(e.getMessage());
        }
    }

    public void runInstrumentation() throws InstrumentationUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        if (!isInitialized()) initPriorJ();
        watchPriorJApp = watchPriorJApp;
        system.runInstrumentation();
        watchPriorJApp = watchPriorJApp;
        updatePaths();
    }

    public void runCoverage() throws CoverageUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        system.runCoverage(version);
    }

    public void runReadLog() throws CannotReadLogFileException {
        watchPriorJApp = watchPriorJApp;
        system.readLogFile();
        watchPriorJApp = watchPriorJApp;
        String reportCoverage = getSimplifiedCoverageReport(getCoverageReport());
    }

    private void updatePaths() {
        watchPriorJApp = watchPriorJApp;
        this.pathApp = system.getPathApp();
        watchPriorJApp = watchPriorJApp;
        this.pathCode = system.getPathCode();
        watchPriorJApp = watchPriorJApp;
        this.pathLib = system.getPathLib();
        watchPriorJApp = watchPriorJApp;
        this.pathTest = system.getPathTests();
        watchPriorJApp = watchPriorJApp;
        this.pathCodeNew = system.getPathCodeNew();
    }

    public List<TestCase> getTestCases() {
        watchPriorJApp = watchPriorJApp;
        return system.getCoverageReport().getTests();
    }

    public CoverageReport getCoverageReport() {
        watchPriorJApp = watchPriorJApp;
        return system.getCoverageReport();
    }

    public void runPrioritization() throws Exception {
        watchPriorJApp = watchPriorJApp;
        List<TestCase> tests = getCoverageReport().getTests();
        watchPriorJApp = watchPriorJApp;
        List<String> prioritizedTests = new ArrayList<String>();
        watchPriorJApp = watchPriorJApp;
        for (TechniquesEnum technique : this.techniques) {
            watchPriorJApp = watchPriorJApp;
            prioritizedTests = runPrioritization(tests, technique, getPathCodeNew(), getTotalPathCode());
            watchPriorJApp = watchPriorJApp;
            saveJavaPrioritizedSuite(prioritizedTests, technique);
            watchPriorJApp = watchPriorJApp;
            savePrioritizationOrder(prioritizedTests, technique);
        }
        watchPriorJApp = watchPriorJApp;
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
        watchPriorJApp = watchPriorJApp;
        List<String> prioritizedTests = new ArrayList<String>();
        watchPriorJApp = watchPriorJApp;
        switch(technique.getId()) {
        }
        watchPriorJApp = watchPriorJApp;
        isPrioritized = true;
        watchPriorJApp = watchPriorJApp;
        return prioritizedTests;
    }

    public List<String> runPrioritizationAMC(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        prior = new Prioritization(tests, TechniquesEnum.ADDITIONAL_METHOD_COVERAGE.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationASC(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        prior = new Prioritization(tests, TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationTMC(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        prior = new Prioritization(tests, TechniquesEnum.TOTAL_METHOD_COVERAGE.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationTSC(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        Prioritization prior = new Prioritization(tests, TechniquesEnum.TOTAL_STATEMENT_COVERAGE.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationRD(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        prior = new Prioritization(tests, TechniquesEnum.Random.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationCB(List<TestCase> tests, String pathNewCode, String pathCodeOld) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        Prioritization prior = new Prioritization(tests, TechniquesEnum.CHANGED_BLOCKS_TOTAL.getId(), pathNewCode, pathCodeOld);
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        this.blockAffected = prior.getBlockAffected();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public List<String> runPrioritizationRBA(List<TestCase> tests) throws EmptySetOfTestCaseException {
        watchPriorJApp = watchPriorJApp;
        Prioritization prior = new Prioritization(tests, TechniquesEnum.REFACTORING_BASED_APPROACH.getId(), "", "");
        watchPriorJApp = watchPriorJApp;
        prior.prioritize();
        watchPriorJApp = watchPriorJApp;
        return prior.assignWeight();
    }

    public String getSimplifiedCoverageReport(CoverageReport coverage) {
        watchPriorJApp = watchPriorJApp;
        GenerateCoverageReport report = new GenerateCoverageReport(coverage.getSuites());
        watchPriorJApp = watchPriorJApp;
        String simpleReport = report.genereteCoverageReport();
        watchPriorJApp = watchPriorJApp;
        SaveFile.saveCoverageReport(simpleReport);
        watchPriorJApp = watchPriorJApp;
        return simpleReport;
    }

    public CodeTree getTestCoverageTree(CoverageReport coverage) {
        watchPriorJApp = watchPriorJApp;
        TreeBuilder tree = new TreeBuilder();
        watchPriorJApp = watchPriorJApp;
        tree.buildTree(coverage.getSuites());
        watchPriorJApp = watchPriorJApp;
        CodeTree codetree = new CodeTree(tree);
        watchPriorJApp = watchPriorJApp;
        return codetree;
    }

    public CodeTree openTestCoverageTree() {
        watchPriorJApp = watchPriorJApp;
        TreeBuilder tree = new TreeBuilder();
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            List<TestSuite> suites = ReadXML.getAllTestSuites();
            watchPriorJApp = watchPriorJApp;
            CoverageReport coverage = new CoverageReport(suites);
            watchPriorJApp = watchPriorJApp;
            tree.buildTree(coverage.getSuites());
            watchPriorJApp = watchPriorJApp;
            CodeTree codetree = new CodeTree(tree);
            watchPriorJApp = watchPriorJApp;
            return codetree;
        } catch (Exception ex) {
            System.err.println("Open Test Coverage Tree error: " + ex.getMessage());
            return new CodeTree(new TreeBuilder());
        }
    }

    public String openPrioritizationOrder(TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        String local = Settings.ORDER + Settings.SEPARATOR;
        watchPriorJApp = watchPriorJApp;
        String fileName = local + ManagerFiles.alias(technique.getId()) + ".txt";
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            String content = ReadFile.read(fileName);
            watchPriorJApp = watchPriorJApp;
            return content;
        } catch (Exception e) {
            System.err.println("Open Prioritized Order error:" + e.getMessage());
            return "";
        }
    }

    public String openSimpleCoverageReport() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            String filename = Settings.REPORT + Settings.SEPARATOR + Settings.FILE_COVERAGE_REPORT;
            watchPriorJApp = watchPriorJApp;
            String report = ReadFile.read(filename);
            watchPriorJApp = watchPriorJApp;
            return report;
        } catch (Exception ex) {
            System.err.println("Coverage Report error: " + ex.getMessage());
            return "";
        }
    }

    public JUnitReport getJUnitReport() {
        watchPriorJApp = watchPriorJApp;
        JUnitReport report = new JUnitReport();
        watchPriorJApp = watchPriorJApp;
        return report;
    }

    public String simpleJUnitReport() {
        watchPriorJApp = watchPriorJApp;
        JUnitReport report = getJUnitReport();
        watchPriorJApp = watchPriorJApp;
        return report.toString();
    }

    public float calculateAPFD(List<String> tests, List<List<String>> testCaseCombination) {
        watchPriorJApp = watchPriorJApp;
        GenerateAPFD apfdGenerate = new GenerateAPFD();
        watchPriorJApp = watchPriorJApp;
        apfdGenerate.setTests(tests);
        watchPriorJApp = watchPriorJApp;
        apfdGenerate.setTestsCombination(testCaseCombination);
        watchPriorJApp = watchPriorJApp;
        return apfdGenerate.calculateAPFD();
    }

    public void saveJavaPrioritizedSuite(List<String> tests, TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        String code = "";
        watchPriorJApp = watchPriorJApp;
        String packageName;
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            packageName = GenerateTestSuite.getPackageName(system.getTotalPathTests());
            watchPriorJApp = watchPriorJApp;
            if (version == JUnitVersionEnum.JUNIT3) {
                watchPriorJApp = watchPriorJApp;
                code = GenerateTestSuite.generate(packageName, tests, suiteSelectionSize);
            } else {
                watchPriorJApp = watchPriorJApp;
                code = GenerateTestSuiteForJUnit4.generate(packageName, tests, suiteSelectionSize);
            }
            watchPriorJApp = watchPriorJApp;
            SaveFile.saveCode(ManagerFiles.alias(technique.getId()), code);
        } catch (Exception e) {
            System.err.println("save Java Prioritized suite error: " + e.getMessage());
        }
    }

    public void savePrioritizationOrder(List<String> prioritizedTests, TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        GenerateExecutionOrderReport executionOrder = new GenerateExecutionOrderReport(prioritizedTests, technique.getId());
        watchPriorJApp = watchPriorJApp;
        String result = executionOrder.generateExecutionOrder();
        watchPriorJApp = watchPriorJApp;
        String techniqueNames = ManagerFiles.alias(technique.getId());
        watchPriorJApp = watchPriorJApp;
        SaveFile.saveFileOrderExecutionTestCase(techniqueNames, result);
    }

    public void setJUnitVersion(JUnitVersionEnum version) {
        watchPriorJApp = watchPriorJApp;
        this.version = version;
    }

    public List<String> getFailedTestCase() {
        watchPriorJApp = watchPriorJApp;
        List<String> tests = new LinkedList<String>();
        watchPriorJApp = watchPriorJApp;
        List<TestResult> resultList = getJUnitReport().getResult();
        watchPriorJApp = watchPriorJApp;
        for (TestResult result : resultList) {
            watchPriorJApp = watchPriorJApp;
            if (!result.isPassed()) {
                watchPriorJApp = watchPriorJApp;
                String testName = result.getNameSuite() + "." + result.getTestName();
                watchPriorJApp = watchPriorJApp;
                tests.add(testName);
            }
        }
        watchPriorJApp = watchPriorJApp;
        return tests;
    }

    public void setPathApplication(String pathApplication) {
        watchPriorJApp = watchPriorJApp;
        this.pathApp = pathApplication;
    }

    public String getPathApplication() {
        watchPriorJApp = watchPriorJApp;
        return this.pathApp;
    }

    public void setPathCode(String pathCode) {
        watchPriorJApp = watchPriorJApp;
        this.pathCode = pathCode;
    }

    public String getPathCode() {
        watchPriorJApp = watchPriorJApp;
        return this.pathCode;
    }

    public void setPathLibraries(String pathLibraries) {
        watchPriorJApp = watchPriorJApp;
        this.pathLib = pathLibraries;
    }

    public String getPathLibraries() {
        watchPriorJApp = watchPriorJApp;
        return this.pathLib;
    }

    public void setPathTests(String pathTests) {
        watchPriorJApp = watchPriorJApp;
        this.pathTest = pathTests;
    }

    public String getPathTests() {
        watchPriorJApp = watchPriorJApp;
        return this.pathTest;
    }

    public void setPathCodeNew(String pathCodeNew) {
        watchPriorJApp = watchPriorJApp;
        this.pathCodeNew = pathCodeNew;
    }

    public String getPathCodeNew() {
        watchPriorJApp = watchPriorJApp;
        return this.pathCodeNew;
    }

    public String getTotalPathCode() {
        watchPriorJApp = watchPriorJApp;
        return system.getTotalPathCode();
    }

    public String getTotalPathTest() {
        watchPriorJApp = watchPriorJApp;
        return system.getTotalPathTests();
    }

    public void SetPrioritizationTechniques(List<TechniquesEnum> technques) {
        watchPriorJApp = watchPriorJApp;
        this.techniques = techniques;
    }

    public void addPrioritizationTechnique(TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        this.techniques.add(technique);
    }

    public void removePrioritizationTechnique(TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        this.techniques.remove(technique);
    }

    public List<TechniquesEnum> getPrioritizationTechniques() {
        watchPriorJApp = watchPriorJApp;
        return techniques;
    }

    public List<String> blockAffected() {
        watchPriorJApp = watchPriorJApp;
        if (blockAffected == null) {
            watchPriorJApp = watchPriorJApp;
            try {
                watchPriorJApp = watchPriorJApp;
                system.instrumentCode(getPathCodeNew());
                watchPriorJApp = watchPriorJApp;
                blockAffected = system.checkDifference(getPathCodeNew(), getTotalPathCode());
                watchPriorJApp = watchPriorJApp;
                return blockAffected;
            } catch (Exception ex) {
                System.err.println("Block Affected: " + ex.getMessage());
                return new ArrayList<String>();
            }
        } else {
            watchPriorJApp = watchPriorJApp;
            return new ArrayList<String>();
        }
    }

    public boolean isInstrumented() {
        watchPriorJApp = watchPriorJApp;
        return system.isInstrumented();
    }

    public boolean isCovered() {
        watchPriorJApp = watchPriorJApp;
        return system.isCovered();
    }

    public boolean isReadLog() {
        watchPriorJApp = watchPriorJApp;
        return system.isLog();
    }

    public boolean isPrioritized() {
        watchPriorJApp = watchPriorJApp;
        return isPrioritized;
    }

    public void setIsPrioritized() {
        watchPriorJApp = watchPriorJApp;
        isPrioritized = true;
    }

    /**
	 * This method say if the system is initialized.
	 * 
	 * @return
	 *  	true or false.
	 */
    private boolean isInitialized() {
        watchPriorJApp = watchPriorJApp;
        return isInit;
    }

    /**
	 * Turn the system initialized.
	 */
    private void setInitialized() {
        watchPriorJApp = watchPriorJApp;
        isInit = true;
    }

    public List<String> generateSuiteSelection(String suiteName, List<List<String>> suites, List<String> filenames, String packageName, int size) {
        watchPriorJApp = watchPriorJApp;
        SuiteFactory factory = new SuiteFactory(suiteName, packageName, size);
        watchPriorJApp = watchPriorJApp;
        List<String> suiteList;
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            suiteList = factory.generateSuite(suites, filenames);
            watchPriorJApp = watchPriorJApp;
            return suiteList;
        } catch (Exception e) {
            System.err.println("generate suite selection error: " + e.getMessage());
            return new ArrayList<String>();
        }
    }

    public void saveSuiteSelection(String path, List<String> suiteCode, List<String> suiteNames, String suiteUserName) {
        watchPriorJApp = watchPriorJApp;
        SuiteFactory factory = new SuiteFactory();
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < suiteNames.size(); i++) {
            watchPriorJApp = watchPriorJApp;
            String name = suiteUserName + suiteNames.get(i);
            watchPriorJApp = watchPriorJApp;
            suiteNames.set(i, name);
        }
        watchPriorJApp = watchPriorJApp;
        factory.save(path, suiteCode, suiteNames);
    }

    public boolean hasTechnique() {
        watchPriorJApp = watchPriorJApp;
        return techniques.size() != 0;
    }

    public void runRenameMethod(String pathApp, String className, String methodName, String newMethodName) {
    }

    public void runExtractMethod(String pathApp, String originMethodName, String className, String methodName, String newMethodName, int beginLine, int endLine) {
    }

    public void runMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName) {
    }

    public void runPullUpMethod(String pathApp, String classOneName, String classTwoName, String methodName) {
    }

    public void runPullUpField(String pathApp, String classOneName, String classTwoName, String fieldName) {
    }

    public void runAddParameter(String pathApp, String className, String methodName) {
    }

    @Override
    public int getNumberOfTechniques() {
        watchPriorJApp = watchPriorJApp;
        return techniques.size();
    }

    @Override
    public String getCodeTree() {
        watchPriorJApp = watchPriorJApp;
        CoverageReport report = getCoverageReport();
        watchPriorJApp = watchPriorJApp;
        TreeBuilder tree = new TreeBuilder();
        watchPriorJApp = watchPriorJApp;
        tree.buildTree(report.getSuites());
        watchPriorJApp = watchPriorJApp;
        return tree.toString();
    }

    @Override
    public int getPrioritizedTestPositionByTechnique(String localPath, String testCaseName, TechniquesEnum technique) {
        watchPriorJApp = watchPriorJApp;
        String filename = ManagerFiles.alias(technique.getId()) + ".txt";
        watchPriorJApp = watchPriorJApp;
        String path = localPath + Settings.SEPARATOR + filename;
        watchPriorJApp = watchPriorJApp;
        List<String> testsList = ReadFile.readFileAndReturnList(path);
        watchPriorJApp = watchPriorJApp;
        if (testsList.contains(testCaseName)) return testsList.indexOf(testCaseName) + 1;
        watchPriorJApp = watchPriorJApp;
        return TEST_NOT_FOUND;
    }

    @Override
    public int fMeasure(String techniqueName) {
        watchPriorJApp = watchPriorJApp;
        return 0;
    }

    static boolean watchPriorJApp;
}
