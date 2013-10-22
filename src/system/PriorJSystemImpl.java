package system;

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
import java.io.File;
import java.io.IOException;
import java.util.List;

import project.JUnitVersionEnum;
import report.CoverageReport;
import util.CopyFile;
import util.FileManager;
import util.Reader;
import util.RunAnt;
import util.PathTo;
import util.SubstituiStringArquivo;
import core.DifferenceApp;
import core.InstrumentApp;
import coverage.TestCase;
import coverage.TestSuite;
import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;



/**
 * Class that controls the entire process of prioritization using other classes of the system.
 * 
 * @author Samuel Santos
 * @author Julio Henrique
 *
 */
public class PriorJSystemImpl implements PriorJSystem {

	/**
	 * This path is the application
	 */
    private String pathApp = "";
    /**
     * This path is the source code
     */
    private String pathCode = "";
    /**
     * This path is a new code version
     */
    private String pathCodeNew = "";
    /**
     * This path is the JUnit tests
     */
    private String pathTests = "";
    /**
     * This path is the dependency libraries.
     */
    private String pathLib = "";
   
    private List blocoAfetado;
    
    /**
     * This object has all information about coverage.
     */
    private CoverageReport coverage;
    
    private String reportCoverage="";
   
    /**
     * this variable say if the application is instrumented.
     */
    private boolean isInstrumented;
    /**
     * This variable say if the coverage process is done.
     */
    private boolean isCovered;
   
    
   /**
    * Constructor SystemImpl.
    *
    * @param pathApp  
    * 			Path where is the application.
    * @param pathCode 
    * 			Path where is the source code of the application.
    * @param pathTest 
    * 			Path wave is the test suite.
    */
    public PriorJSystemImpl(String pathApp, String pathCode, String pathTest, String pathLib) {
        this(pathApp, pathCode, pathTest, "", pathLib); 
    }

   /**
    * Constructor SystemImpl.
    *
    * @param pathApp 
    * 			Path where the application is.
    * @param pathCode 
    * 			Path where is the source code of the application.
    * @param pathTest 
    * 			Path wave is the test suite.
    * @param pathCodeNew
    * 			Path compared to the new code.
    * @param pathLib 
    * 			Path to the libraries used.
    */
    public PriorJSystemImpl(String pathApp, String pathCode, String pathTest, String pathCodeNew, String pathLib) {
        this.pathApp = pathApp;
        this.pathCode = pathCode;
        this.pathCodeNew = pathCodeNew;
        this.pathTests = pathTest;
        
        if (pathLib.isEmpty()) 
        	this.pathLib = "lib";
        else
        	this.pathLib = pathLib;
        
        isCovered = false;
        isInstrumented = false;
        
    }
    /**
     * Default construct.
     */
    public PriorJSystemImpl(){
    	//empty
    }
    
    public void instrumentCode(String path) throws Exception {
        InstrumentApp inst = new InstrumentApp(path, pathTests);
        inst.run();
        
    }
    
    public List checkDifference(String pathCodeNew, String pathCodeOld) throws Exception {
        DifferenceApp diff = new DifferenceApp (pathCodeOld, pathCodeNew);
        diff.run();
        List<String> diferencas = diff.getListDiff();
       
        return diferencas;
    }
    
    public void copyBackupFiles(String pathOrigemApp) throws IOException, Exception {
        File origem = new File(pathOrigemApp);
        
        File dirDestino = new File(PathTo.WORKSPACE+ PathTo.SEPARATOR);
        
        if (!dirDestino.exists()) {
            dirDestino.mkdir();
        }
        
        File destino = new File(PathTo.WORKSPACE+ PathTo.SEPARATOR + origem.getName());
        CopyFile.copyAll(origem, destino, true);
        setPathApp(destino.toString());
        
        File origemNewCode=null;
        
        if (!pathCodeNew.isEmpty()){
        	origemNewCode = new File(pathCodeNew);
        	
        	destino = new File(PathTo.WORKSPACE+ PathTo.SEPARATOR + origemNewCode.getName());
        	
            CopyFile.copyAll(origemNewCode, destino, true);
           
            setPathCodeNew(destino.toString());
        }
      
    }
    
    public void copyFiles() throws IOException {
        
        File origem = new File(PathTo.ASPECT_FILE + PathTo.SEPARATOR );
     
        File destino = new File(getPathApp()+ PathTo.SEPARATOR + "src");
        CopyFile.copyAll(origem, destino, true);

        File ori = new File(PathTo.EXTERNAL +PathTo.SEPARATOR+"lib"+PathTo.SEPARATOR);
        File des = new File(getPathApp() + PathTo.SEPARATOR + getPathLib() + PathTo.SEPARATOR);
        CopyFile.copyAll(ori, des, true);
    }
    
    public void setPathAspectFile(JUnitVersionEnum version) {
    	
        File arquivo = new File(getPathApp() + PathTo.SEPARATOR + "src"+PathTo.SEPARATOR+"AspectCoverage.aj");
        
        try {
        	String testPath = getPathTests();
        	
        	if (testPath.contains(PathTo.SEPARATOR))
        		testPath = getPathTests().substring(getPathTests().lastIndexOf(PathTo.SEPARATOR), getPathTests().length()).replace(PathTo.SEPARATOR, "");
                    	
            String pointcutAspect = "";
            
            if (version==JUnitVersionEnum.JUNIT3){
                pointcutAspect = "* *.test*(..)";
            }
            else{
                pointcutAspect = "@Test * *(..)";
            }
            
            SubstituiStringArquivo.setPath(arquivo, "*", testPath, pointcutAspect);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public void deleteAspectosFile() {
        String path = getPathApp() + PathTo.SEPARATOR+ "src" + PathTo.SEPARATOR + "AspectCoverage.aj";
        
        FileManager.deleteFile(path);
    }
    
    public void executaAnt(String pathApp, String pathCode, String pathTest, String pathLib) {
        RunAnt run = new RunAnt(pathApp, pathCode, pathTest, pathLib);
        run.run();
    }
    
    public void readLogFile() throws CannotReadLogFileException {
              
    	try{
	        Reader rd = new Reader(PathTo.REPORT+PathTo.SEPARATOR+"coveragePriorJ");
	
	        List<TestSuite> suites = (List<TestSuite>) rd.read();
	        
	        coverage =  new CoverageReport();
	        
	        coverage.buildReport(suites);
	        
	        //this method create a coverage file.
	        coverage.buildFileConfig(suites);
	        
    	}
    	catch(Exception ex){
    		throw new CannotReadLogFileException(ex.getMessage());
    	}
    }
    
    public void runInstrumentation() throws InstrumentationUnrealizedException {
    	try{
    		copyBackupFiles(getPathApp());
    		instrumentCode(getTotalPathCode());
    		
    		if (!getPathCodeNew().isEmpty())
    			instrumentCode(getPathCodeNew());
    		
    		isInstrumented = true;
    	}
    	catch(Exception ex){
    		throw new InstrumentationUnrealizedException(ex.getMessage());
    	}
        
    }
    
    public void runCoverage(JUnitVersionEnum version) throws CoverageUnrealizedException {
        try {
	    	copyFiles();
	        setPathAspectFile(version);
	        executaAnt(getPathApp(), getPathCode(), getPathTests(), getPathLib());
	        deleteAspectosFile();
	        isCovered = true;
        }
        catch(Exception ex){
        	throw new CoverageUnrealizedException(ex.getMessage());
        }
        
    }
    
    public void runReadLog() throws CannotReadLogFileException {
        try {
            readLogFile();
        } catch (Exception e) {
            throw new CannotReadLogFileException(e.getMessage());
        }
    }
    
    public CoverageReport getCoverageReport(){
    	return this.coverage;
    }
    
    public List<TestCase> getTests(){
        return coverage.getTests();
    }
    
    public String getPathCode() {
        return pathCode;
    }
    
    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }
    
    public String getPathTests() {
        return pathTests;
    }
    
    public void setPathTests(String pathTests) {
        this.pathTests = pathTests;
    }
    
    public String getPathCodeNew() {
        return pathCodeNew;
    }
    
    public void setPathCodeNew(String pathCodeNew) {
        this.pathCodeNew = pathCodeNew;
    }
    
    public String getPathApp() {
        return pathApp;
    }
    
    public void setPathApp(String pathApp) {
        this.pathApp = pathApp;
    }
    
    public String getTotalPathCode() {
        return getPathApp() + PathTo.SEPARATOR + getPathCode();
    }
    
    public String getTotalPathTests() {
        return getPathApp() + PathTo.SEPARATOR+ getPathTests();
    }
    
    public String getPathLib() {
        return pathLib;
    }
    public void setPathLib(String pathLib) {
        this.pathLib = pathLib;
    }
    
    public boolean isInstrumented(){
    	return isInstrumented;
    }
    
    public boolean isCovered(){
    	return isCovered;
    }
    
    public boolean isLog(){
    	if (!isInstrumented || !isCovered)
    		return false;
    	
    	return !getCoverageReport().getSuites().isEmpty();
    }
   
    
    public boolean hasPathApplication(){
    	return !pathApp.isEmpty();
    }
    
    public boolean hasPathCode(){
    	return !pathCode.isEmpty();
    }
    
    public boolean hasPathTest(){
    	return !pathTests.isEmpty();
    }
   
    public boolean hasPathCodeNew(){
    	return !pathCodeNew.isEmpty();
    }

	
    
}

