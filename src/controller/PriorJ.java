package controller;

import java.util.ArrayList;
import java.util.List;

import com.java.io.JavaIO;

import manager.Coverage;
import report.GenerateCoverageReport;
import report.GenerateExecutionOrderReport;
import report.GenerateTestSuite;
import technique.EmptySetOfTestCaseException;
import technique.Technique;
import technique.TechniqueCreator;
import technique.TechniqueEchelonTotal;
import core.Difference;
import core.DifferenceApp;
import core.InstrumentApp;
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
	private static List<String> affectedBlocks;
	
	public static PriorJ getInstance(){
		if (PriorJ.instance == null){
			techniques = new ArrayList<Integer>();
			affectedBlocks = new ArrayList<String>();
			PriorJ.instance = new PriorJ();
		}
		return PriorJ.instance;
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
		if (typeOfTechnique == TechniqueCreator.CHANGED_BLOCKS){
			TechniqueEchelonTotal technique = new TechniqueEchelonTotal();
			technique.setBlockAffected(affectedBlocks);
			return technique.prioritize(allTests);
			
		}
		else{
			Technique technique = creator.create(typeOfTechnique);
			return technique.prioritize(allTests);
		}
	}
	
	/**
	 * This method prioritize with many techniques simultaneously.
	 * 
	 * @throws Exception 
	 * 
	 */
	public void prioritizeAll(List<TestCase> allTests) throws Exception {
		String slash = JavaIO.SEPARATOR;
		for (Integer typeOfTechnique : techniques){
			//getting the suite names
			String acronyms = TechniqueCreator.acronyms(typeOfTechnique);
			//prioritize the tests.
			List<String> prioritizedList = prioritize(typeOfTechnique, allTests);
			//saving the produced artifacts
			String order = createOrderReport(typeOfTechnique, prioritizedList);
			DataManager.save(acronyms+".js","js", order);
			String suite = createSuite(acronyms, prioritizedList);
			DataManager.save(acronyms+".java", suite);
		}
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
	 * Create a coverage report.
	 *  
	 * @param suites
	 * @return
	 */
	public String createCoverageReport(List<TestSuite> suites) {
		GenerateCoverageReport textReport = new GenerateCoverageReport(suites);
		return textReport.generateCoverageReport();
	}
	
	/**
	 * This method instrument a Folder and sub folders.
	 * 
	 * @param string
	 * @throws Exception 
	 */
	public void instrument(String filePath) throws Exception {
		 InstrumentApp inst = new InstrumentApp(filePath);
	     inst.run();
	}
	
	/**
	 * Check differences between two versions of same code.
	 * 
	 * @param pathCodeNew
	 * @param pathCodeOld
	 * @return
	 * @throws Exception
	 */
	public List<String> checkDifference(String filePath, String oldFilePath) throws Exception {
		Difference difference = new Difference(filePath, oldFilePath);
		difference.diff();
		List<String> affected = difference.getStatementsDiff();
        return affected;
	}

	public void setAffectedBlocks(List<String> blocks) {
		this.affectedBlocks = blocks;
	}	
	
	public static void main(String[] args) {
		System.out.println("PriorJ");
	}

}