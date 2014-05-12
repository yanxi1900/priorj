package controller;

import java.util.ArrayList;
import java.util.List;

import manager.Coverage;
import report.GenerateExecutionOrderReport;
import report.GenerateTestSuite;
import technique.EmptySetOfTestCaseException;
import technique.Technique;
import technique.TechniqueCreator;

import com.java.io.JavaIO;

import coverage.TestCase;
import coverage.TestSuite;

/**
 * Class PriorJ priorization of JUnit Test Cases. 
 *  
 * @author Samuel T. C. Santos
 *
 */
public class PriorJ {

	private static PriorJ instance;
	private static List<Integer> techniques;
	
	private static String localbase;
	private static String projectFolder;
	@SuppressWarnings("unused")
	private static String versionFolder;
	
	private final String slash = JavaIO.SEPARATOR;
	
	public static PriorJ getInstance(){
		if (PriorJ.instance == null){
			techniques = new ArrayList<Integer>();
			localbase = "";
			projectFolder = "";
			versionFolder = "";
			PriorJ.instance = new PriorJ();
		}
		return PriorJ.instance;
	}

	/**
	 * Get the location where the artifacts are saved.
	 * 
	 * @return
	 */
	public String getLocalBasePath() {
		return localbase;
	}

	/**
	 * Adding the selected Technique by user.
	 * 
	 * @param typeOfTechnique
	 */
	public void addTechnique(int typeOfTechnique) {
		if (typeOfTechnique == TechniqueCreator.ADDITIONAL_METHOD_COVERAGE){
			techniques.add(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		}
		else if(typeOfTechnique == TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE){
			techniques.add(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		}
		else if (typeOfTechnique == TechniqueCreator.CHANGED_BLOCKS){
			techniques.add(TechniqueCreator.CHANGED_BLOCKS);
		}
		else if (typeOfTechnique == TechniqueCreator.RANDOM){
			techniques.add(TechniqueCreator.RANDOM);
		}
		else if (typeOfTechnique == TechniqueCreator.TOTAL_METHOD_COVERAGE){
			techniques.add(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		}
		else if (typeOfTechnique == TechniqueCreator.TOTAL_STATEMENT_COVERAGE){
			techniques.add(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		}
		else{
			throw new IllegalArgumentException("Invalid Technique Type!");
		}
	}
	
	/**
	 * Removing a selected technique.
	 * 
	 * @param typeOfTechnique
	 */
	public void removeTechnique(int typeOfTechnique){
		if (techniques.contains(typeOfTechnique)){
			int index = techniques.indexOf(typeOfTechnique);
			techniques.remove(index);
		}
	}

	/**
	 * The list of selected Techniques Types.
	 * 
	 * @return
	 */
	public List<Integer> getTechniques() {
		return techniques;
	}

	

	/**
	 * This method save Coverage Data to XML file.
	 * 
	 * @param localPath
	 * @param fileName
	 * @param allSuites
	 */
	public void saveCoverageData(String localPath, String fileName,	@SuppressWarnings("rawtypes") List<List> allSuites) {
		JavaIO.saveObjectToXML(localPath, fileName, allSuites, false);
	}

	/**
	 * Opening the coverage file and retrieve coverage data.
	 * 
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<List> openCoverageData(String filePath) {
		@SuppressWarnings("unchecked")
		List<List> coverage = (List<List>) JavaIO.getObjectFromXML(filePath);
		return coverage;
	}

	/**
	 * Getting a list with all  Test Suites.
	 * 
	 * @param allSuites
	 * @return
	 */
	public List<TestSuite> getTestSuites(@SuppressWarnings("rawtypes") List<List> allSuites) {
		Coverage coverage = new Coverage();
		return coverage.getSuiteList(allSuites);
	}

	/**
	 * Getting a list with all Test Cases.
	 * 
	 * @param suites
	 * @return
	 */
	public List<TestCase> getTestCases(List<TestSuite> suites) {
		Coverage coverage = new Coverage();
		return coverage.getAllTests(suites);
	}

	/**
	 * 
	 * @param totalMethodCoverage
	 * @param allTests
	 * @return
	 * @throws EmptySetOfTestCaseException 
	 */
	public List<String> prioritize(int typeOfTechnique, List<TestCase> allTests) throws EmptySetOfTestCaseException {
		TechniqueCreator creator = new TechniqueCreator();
		Technique technique = creator.create(typeOfTechnique);
		return technique.prioritize(allTests);
	}
	
	/**
	 * This method prioritize with many techniques simultaneously.
	 * @throws Exception 
	 * 
	 */
	public void prioritizeAll(List<TestCase> allTests) throws Exception {
		//List<TestCase> tests = openCoverageReport().getTestCases();
		for (Integer typeOfTechnique : techniques){
			//getting the suite names
			String acronyms = TechniqueCreator.acronyms(typeOfTechnique);
			//prioritize the tests.
			List<String> prioritizedList = prioritize(typeOfTechnique, allTests);
			//saving the produced artifacts
			//String order = createOrder(typeOfTechnique, prioritizedList);
			//saveOrder(acronyms, order);
			//String suite = createSuite(acronyms, prioritizedList);
			//saveSuite(acronyms, suite);
		}
	}
	
	
	@SuppressWarnings("static-access")
	public String getProjectFolderName(){
		return this.projectFolder;
	}
	
	
	/**
	 * Creating the local base folder.
	 * 
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public void createLocalbase(String path) throws Exception {
		if (path != null && !path.isEmpty()){
			this.localbase = path;
			JavaIO.createFolder(path);
		}
		else{
			throw new Exception("Invalid Path!");
		}
	}
	
	/**
	 * This method create a project folder inside the local base.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public void createProjectFolder(String folderName) throws Exception{
		if(localbase.isEmpty())
			throw new Exception("Set local base path!");
		
		this.projectFolder = folderName;
		JavaIO.createFolder(localbase+slash+folderName);
	}

	/**
	 * Create a sub folder to save prioritized version to same project.
	 * 
	 * @param projectFolder
	 * @param versionFolder
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	public void createFolderVersion(String projectFolder, String versionFolder) throws Exception {
		if (localbase.isEmpty())
			throw new Exception("Set local base path!");
		
		if ( validate(projectFolder) && validate(versionFolder)){
			this.projectFolder = projectFolder;
			this.versionFolder  = versionFolder;
			JavaIO.createFolder(localbase+slash+projectFolder+slash+versionFolder);
		}
	}
	
	
	/**
	 * Basic validation to a system path!
	 * 
	 * @param path
	 * @return
	 */
	private boolean validate(String path){
		return path != null && !path.isEmpty();
	}

	
	/**
	 * This method create a prioritized test suite from a list of tests.
	 * 
	 * @param suiteName
	 *  the suite name
	 * @param tests
	 *  the prioritized test order
	 * @return
	 *  the suite code
	 * @throws Exception 
	 */
	public String createSuite(String suiteName, List<String> tests) throws Exception{
		return GenerateTestSuite.generate("tests", suiteName, tests);
	}
	
	/**
	 * Create the order report.
	 * 
	 * @param listTests
	 *   a list of prioritized tests names.
	 *   
	 * @return
	 *  the order report.
	 *  
	 * @throws Exception 
	 */
	public String createOrderReport(int typeOfTechnique, List<String> listTests) throws Exception {
		return GenerateExecutionOrderReport.create(typeOfTechnique,listTests);
	}

	/**
	 * This method save 
	 * @param string
	 * @param report
	 */
	public void save(String filename, String content) {
		JavaIO.createTextFile(localbase+slash+projectFolder+ slash +versionFolder, filename, content, false);
	}
	
	
}
