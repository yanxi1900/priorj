package controller;

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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.JUnitVersionEnum;
import report.CodeTree;
import report.CoverageReport;

import technique.TechniquesEnum;
import util.FileManager;
import util.ReadFile;
import util.PathTo;

import main.PriorJ;
import main.PriorJImpl;

import coverage.TestCase;

import apfd.ChartAPFD;



import exception.*;



/**
 * This class is a controller to PriorJ and PriorJProjectManager.
 * 
 * @author Samuel T. C. Santos
 */
public class PriorJController {
    /**
     * The paths used by controller.
     */
    private String pathApp="";
    private String pathCode="";
    private String pathNew="";
    private String path = "";
    private String pathTests="";
    private String pathLib="";
    
    /**
     * The priorJ
     */
    private PriorJ priorj;
    
    private final String EMPYT_STRING = "";
    /**
     * APFD Input combinations
     */
    private List<List<String>> combinations;
    /**
     * A list of part code affected by changed blocks.
     */
    private List<String> blockAffected ;  
    /**
     * States to controller.
     */
    private boolean isSaved, isChanged;
    
    /**
     * The JUnit version used in the project.
     */
	private JUnitVersionEnum version;
        
    /**
     * Constructor default.
     */
    public PriorJController(){
        init();
    }
    /**
     * Initialization the controller.
     * initial state, save = false and changed = false, not save and not is changed.
     * 
     */
    private void init() {
        priorj = new PriorJImpl();
       
        combinations = new ArrayList<List<String>>();
         
        isSaved = false;
        isChanged = false;
        version = JUnitVersionEnum.JUNIT3;
    }
       
    /**
     * Set the controller paths to PriorJ.
     * 
     */
    public void initPriorJ()  {
        priorj.setPathApplication(pathApp);
        priorj.setPathCode(pathCode);
        priorj.setPathLibraries(pathLib);
        priorj.setPathTests(pathTests);
        priorj.setPathCodeNew(pathNew);
    }
    /**
     * Generate the APFD chart.
     * 
     * @return
     * 		A component JPanel with the generated chart.
     */
    public JPanel generateAPFDChart(){
        
        try {
            
            List<String> seriesName = FileManager.listFilesNames(PathTo.ORDER);
            List<String> testsFailed = priorj.getFailedTestCase();
            
            List<List<String>> orderList = new ArrayList<List<String>>();
            String local = PathTo.ORDER + PathTo.SEPARATOR;
            
            for (String filename: seriesName){
                
                 List<String> tests = ReadFile.readFileAndReturnList(local+filename);
                 orderList.add(tests);
            }
            
            List<String> valuesAPFD = new ArrayList<String>();
            
            for (List<String> list: orderList){
                double apfdValue = priorj.calculateAPFD(list, combinations);
                valuesAPFD.add(String.format("%.2f", apfdValue));
            }
            
            
            //this is a series values to plot a APFD Graph.
            int values [][] = new int[seriesName.size()][testsFailed.size()];
            
            for (int i=0; i< seriesName.size(); i++){
                for (int j=0; j < testsFailed.size(); j++){
                    values[i][j] = findIndex(orderList.get(i), testsFailed.get(j));
                   
                } 
            }
          
            //number of tests in the test suite
            int numberOfTests = orderList.get(0).size();

            //remove extension .txt
            String seriesTitle [] = new String[seriesName.size()]; 
            for (int i=0; i< seriesName.size(); i++){
                seriesTitle[i] = seriesName.get(i).replace(".txt", "") +" (" +valuesAPFD.get(i)+"%)";
            }
            
            ChartAPFD demoChart = new ChartAPFD("APFD", seriesTitle, values, numberOfTests);
            
            return demoChart.getChart();
            
        } catch (Exception ex) {
            System.err.println("APFD Chart error: "+ ex.getMessage());
            return new JPanel();
        }
           
    }
    
    private int findIndex(List<String> list, String testName){
        int index = 0;
        for (int i=0; i<list.size(); i++){
            if (list.get(i).equals(testName)){
                index = i; 
                break;
            }
        }
        return index+1;
    }
    
    public double generateAPFD( List<List<String>> combinations ){
        
        List<String> orders;
        //this value is used to generate all apdf values to apfd chart.
        setCombinations(combinations);
        
        try {
            orders = FileManager.listFilesNames(PathTo.ORDER);
           
            List<String> tests = ReadFile.readFileAndReturnList(PathTo.ORDER + PathTo.SEPARATOR + orders.get(0));

            return priorj.calculateAPFD(tests, combinations);
        } catch (Exception ex) {
           System.err.println("Generate APFD error " + ex.getMessage());
            return 0.0;
        }
        
    }
    
    public List<String> affectedBlocks(){
        return priorj.blockAffected();
    }
    
    private void setCombinations(List<List<String>> combinations){
        this.combinations = combinations;
    }
    
    public void setJUnitVersion(JUnitVersionEnum version){
        //priorj.setJUnitVersion(version);
    	this.version = version;
    }
    
    public JUnitVersionEnum getJUnitVersion(){
    	return version;
    }
   
    public void runTestCoverageReport(String pathApp, String pathCode, String pathLib, String pathTest){
    	try {
                priorj.runTestCoverageReport(pathApp, pathCode, pathLib, pathTest);
        } catch (EmptyPathException e) {
                System.err.println(e.getMessage());	
        } catch (DirectoryNotExistException e) {
                System.err.println(e.getMessage());
        }
    }
    
    public void runInstrumentation() throws InstrumentationUnrealizedException {
   		initPriorJ();
   		priorj.runInstrumentation(); 
    }
    
    public void runCoverage() throws CoverageUnrealizedException, InstrumentationUnrealizedException  {
    	if (!priorj.isInstrumented())
    		runInstrumentation();
    	
        priorj.runCoverage();    
    }
    
    public void runReadLog() throws CannotReadLogFileException, InstrumentationUnrealizedException, CoverageUnrealizedException {
    	if (!priorj.isInstrumented())
    		runInstrumentation();
    	
    	if (!priorj.isCovered())
    		runCoverage();
    	
        priorj.runReadLog();
    }
    
    public void runPrioritization() throws Exception  {     
    	if (!priorj.isInstrumented())
    		runInstrumentation();
    	
    	if (!priorj.isCovered())
    		runCoverage();
    	
    	if (!priorj.isReadLog())
    		runReadLog();
    	
    	if (!priorj.hasTechnique())
    		throw new Exception("Prioritization Techniques not selected!");
    	
        priorj.runPrioritization();
        
        setIsChanged();
    }

    public List<String> runPrioritizationTMC(){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationTMC(tests);
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public List<String> runPrioritizationTSC(){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationTSC(tests);
			
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public List<String> runPrioritizationASC(){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationASC(tests);
			
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public List<String> runPrioritizationAMC(){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationAMC(tests);
			
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public List<String> runPrioritizationRND(){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationRD(tests);
			
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public List<String> runPrioritizationCB(String pathNewCode, String pathCodeOld){
    	
    	try {
    		List<TestCase> tests = priorj.getCoverageReport().getTests();
        	setIsChanged();
        	priorj.setIsPrioritized();
			return priorj.runPrioritizationCB(tests, pathNewCode, pathCodeOld);
			
		} catch (EmptySetOfTestCaseException e) {
			System.err.println(e.getMessage());
			return new ArrayList<String>();
		}
    }
    
    public void saveJavaPrioritizedSuite(List<String> tests, TechniquesEnum technique){
    	
    	priorj.saveJavaPrioritizedSuite(tests, technique);
    }
    
    public List<TechniquesEnum> getTechniques() {
        return priorj.getPrioritizationTechniques();
    }
    
    public String getSimpleJUnitReport(){
        return priorj.simpleJUnitReport();
    }

    public void setTechniques(List<TechniquesEnum> techniques) {
        priorj.SetPrioritizationTechniques(techniques);
    }

 
    public List<String> getFailedTests(){
        return priorj.getFailedTestCase();
    }
    
    public String getSimpleCoverageReport(){
        return priorj.openSimpleCoverageReport();
    }
    
    
    public String getPathApp() {
        return pathApp;
    }

    public void setPathApp(String pathApp) {
        this.pathApp = pathApp;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }

    public String getPathNew() {
        return pathNew;
    }

    public void setPathNew(String pathNew) {
        this.pathNew = pathNew;
    }

    public String getPathTests() {
        return pathTests;
    }

    public void setPathTests(String pathTests) {
        this.pathTests = pathTests;
    }

    public String getPathLib() {
        return pathLib;
    }

    public void setPathLib(String pathLib) {
        this.pathLib = pathLib;
    }

    public  String openCoverageReport(){
        return priorj.openSimpleCoverageReport();
    }
 
    public CodeTree getTestCoverageTree(){
        return priorj.openTestCoverageTree();
    }
    
    public void addTechnique(String technique){
        
        if (technique.toLowerCase().equals("tmc"))
            addTechnique(TechniquesEnum.TOTAL_METHOD_COVERAGE);
        else if (technique.toLowerCase().equals("tsc"))
            addTechnique(TechniquesEnum.TOTAL_STATEMENT_COVERAGE);
        else if (technique.toLowerCase().equals("amc"))
            addTechnique(TechniquesEnum.ADDITIONAL_METHOD_COVERAGE);
        else if (technique.toLowerCase().equals("asc"))
            addTechnique(TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE);
        else if (technique.toLowerCase().equals("cb"))
            addTechnique(TechniquesEnum.CHANGED_BLOCKS_TOTAL);
        else if (technique.toLowerCase().equals("rnd"))
            addTechnique(TechniquesEnum.Random);
        else if (technique.toLowerCase().equals("rba"))
            addTechnique(TechniquesEnum.REFACTORING_BASED_APPROACH);
        
    }
    
    public void removeTechnique(String technique){
        if (technique.toLowerCase().equals("tmc"))
            removeTechnique(TechniquesEnum.TOTAL_METHOD_COVERAGE);
        else if (technique.toLowerCase().equals("tsc"))
            removeTechnique(TechniquesEnum.TOTAL_STATEMENT_COVERAGE);
        else if (technique.toLowerCase().equals("amc"))
            removeTechnique(TechniquesEnum.ADDITIONAL_METHOD_COVERAGE);
        else if (technique.toLowerCase().equals("asc"))
            removeTechnique(TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE);
        else if (technique.toLowerCase().equals("cb"))
            removeTechnique(TechniquesEnum.CHANGED_BLOCKS_TOTAL);
        else if (technique.toLowerCase().equals("rnd"))
            removeTechnique(TechniquesEnum.Random);
        else if (technique.toLowerCase().equals("rba"))
            removeTechnique(TechniquesEnum.REFACTORING_BASED_APPROACH);
    }
    /**
     * This method do a parse from a string with technique name to a Technique Object.
     * 
     * @param technique
     * @return
     */
    public TechniquesEnum parseTechnique(String technique){
        
    	TechniquesEnum techniqueObject = null;
        
        if (technique.toLowerCase().equals("tmc"))
        	techniqueObject = TechniquesEnum.TOTAL_METHOD_COVERAGE;
        else if (technique.toLowerCase().equals("tsc"))
        	techniqueObject = TechniquesEnum.TOTAL_STATEMENT_COVERAGE;
        else if (technique.toLowerCase().equals("amc"))
        	techniqueObject = TechniquesEnum.ADDITIONAL_METHOD_COVERAGE;
        else if (technique.toLowerCase().equals("asc"))
        	techniqueObject = TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE;
        else if (technique.toLowerCase().equals("cb"))
        	techniqueObject = TechniquesEnum.CHANGED_BLOCKS_TOTAL;
        else if (technique.toLowerCase().equals("rnd"))
        	techniqueObject = TechniquesEnum.Random;
        else if (technique.toLowerCase().equals("rba"))
        	techniqueObject = TechniquesEnum.REFACTORING_BASED_APPROACH;
        
        return techniqueObject;
    }
    
    /**
     * This method generate a suite selection.
     * 
     * @param suiteName
     * @param suites
     * @param suiteNames
     * @param packageName
     * @param size
     * @return
     */
    public List<String> generateSuiteSelection(String suiteName, List<List<String>> suites, List<String> suiteNames, String packageName, int size){
    	List<String> suiteCodeList = priorj.generateSuiteSelection(suiteName, suites, suiteNames, packageName, size);
    	
    	return suiteCodeList;
    }
    
    /**
     * This method save a prioritized test suite.
     * @param path
     * @param suiteCodes
     * @param suiteNames
     * @param userSuiteName
     */
    public void saveSuiteSelection(String path, List<String> suiteCodes, List<String> suiteNames, String userSuiteName ){
    	priorj.saveSuiteSelection(path, suiteCodes, suiteNames, userSuiteName);
    }
    
    /**
     * This method add a new technique.
     * 
     * @param technique
     */
    public void addTechnique(TechniquesEnum technique){
        priorj.addPrioritizationTechnique(technique);
    }
    /**
     * This method remove a technique.
     * 
     * @param technique
     */
    public void removeTechnique(TechniquesEnum technique){
        priorj.removePrioritizationTechnique(technique);
    }
    /**
     * This method say if the PriorJ is null.
     * 
     * @return
     * 		true or false.
     */
    public boolean isNull(){
        return priorj == null;
    }
   
    public boolean isFirstRunTime(){
        return !pathApp.equals("") && !pathCode.equals("") && !pathTests.equals("");
    }
    
    /**
     * This method say if the paths exist in the controller.
     * 
     * @return
     * 		true or false.
     */
    public boolean hasPaths(){
        return !pathApp.equals("") && !pathCode.equals("") && !pathTests.equals("");
    }
    
    /**
     * This method say if the instrumentation is complete.
     * 
     * @return
     * 		true or false
     */
    public boolean isInstrumented(){
        return priorj.isInstrumented();
    }
    /**
     * This method say if the coverage process is complete.
     * 
     * @return
     * 	true or false
     */
    public boolean isCovered(){
        return priorj.isCovered();
    }
    /**
     * This method say if the log file was generated.
     * 
     * @return
     * 		true or false
     */
    public boolean isReadLog(){
        return priorj.isReadLog();
    }
    /**
     * This method say if the prioritization process is done.
     * 
     * @return
     * 		true or false
     */
    public boolean isPrioritized(){
        return priorj.isPrioritized();
    }
    
    public void runRenameMethod(String pathApp, String className, String methodName, String newMethodName){
        priorj.runRenameMethod(pathApp, className, methodName, newMethodName);    
    }
    
    public void runExtractMethod(String pathApp, String originMethodName, String className,
                            String methodName, String newMethodName, int beginLine, int endLine){
        priorj.runExtractMethod(pathApp, originMethodName, className, methodName, newMethodName, beginLine, endLine);
    }
    
    public void runMoveMethod(String pathApp, String classOneName, String classTwoName, String methodName){
        priorj.runMoveMethod(pathApp, classOneName, classTwoName, methodName);
    }
    
    public void runPullUpMethod(String pathApp, String classOneName, String classTwoName, String methodName){
        priorj.runPullUpMethod(pathApp, classOneName, classTwoName, methodName);
    }
    
    public void runPullUpField(String pathApp, String classOneName, String classTwoName, String fieldName){
        priorj.runPullUpField(pathApp, classOneName, classTwoName, fieldName);
    }
    
    public void runAddParameter(String pathApp, String className, String methodName){
        priorj.runAddParameter(pathApp, className, methodName);
    }

    public PriorJ getPriorj() {
            return priorj;
    }

    public void setPriorj(PriorJ priorj) {
            this.priorj = priorj;
    }
    
      public boolean isChanged(){
          return isChanged;
      }
      
      public void setIsChanged(){
           isChanged = true;
      }

    /**
     * This method say if a directory exist.
     * 
     * @param path
     * 		the directory path.
     * 
     * @return
     * 		true or false
     */
	public boolean exist(String path) {
		// TODO Auto-generated method stub
		return FileManager.existFileOrDirectory(path);
	}
	
	public Object getNumberOfTechniques() {
		// TODO Auto-generated method stub
		return priorj.getNumberOfTechniques();
	}
	public String getCodeTree() {
		// TODO Auto-generated method stub
		return priorj.getCodeTree();
	}
	
	/**
	 * This method get the prioritized position to a test case by technique name.
	 * 
	 * @param tests
	 * @param technique
	 * @return
	 */
	public int getPrioritizedTestCasePosition(String testCaseName, String technique){
		
		String localPath = PathTo.ORDER;
		TechniquesEnum techniqueObject = parseTechnique(technique);
		
		return priorj.getPrioritizedTestPositionByTechnique(localPath, testCaseName, techniqueObject);
		
	}
}

