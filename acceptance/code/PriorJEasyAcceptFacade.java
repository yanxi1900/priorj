package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;

import exception.CoverageUnrealizedException;
import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;
import exception.InstrumentationUnrealizedException;

import main.PriorJFacade;

public class PriorJEasyAcceptFacade {

	PriorJFacade facade;

	Map<Integer, TestSuite> suites;
	Map<Integer, TestCase> tests;
	Map<Integer, ClassCode> classCodes;
	Map<Integer, Method> methods;
	Map<Integer, Statement> statements;
	
	private int idSuite;
	private int idTest;
	private int idClassCode;
	private int idMethod;
	private int idStatement;
	
	public PriorJEasyAcceptFacade(){
		facade = new PriorJFacade();
		
		suites = new HashMap<Integer, TestSuite> ();
		tests = new HashMap<Integer, TestCase>();
		classCodes = new HashMap<Integer, ClassCode>();
		methods = new HashMap<Integer, Method>();
		statements = new HashMap<Integer, Statement>();
		
		idSuite = 0;
		idTest = 0;
		idClassCode =0;
		idMethod = 0;
		idStatement = 0;
	}
	
	public void resetSystem(){
		facade.removeProjectAll();
	}
	
	public void createProject(String name, String version) throws Exception {
		facade.createProject(name, version);
	}
	
	public boolean searchProject(String projectName) throws Exception{
		return facade.searchProject(projectName);
	}

	public void removeProject(String projectName){
		facade.removeProject(projectName);
	}
	
	public boolean hasOpenedProject(){
		return facade.hasOpenedProject();
	}
	
	public void closeOpenProject(){
		facade.closeProject();
	}
	
	public void openProject(String projectName, String projectVersion){
		facade.openProject(projectName, projectVersion);
	}
	
	public int numberOfProjects(){
		return facade.numberOfProjects();
	}
	
	public String getAttributeOpenedProject(String attribute) throws Exception{
		return facade.getAttributesOpenedProject(attribute);	
	}
	
	public void setPathApp(String pathApp) throws Exception{
		facade.setPathApp(pathApp);
	}
	
	public void setPathCode(String pathCode) throws Exception{
		facade.setPathCode(pathCode);
	}
	
	public void setPathLib(String pathLib) throws Exception{
		facade.setPathLib(pathLib);
	}
	
	public void setPathTest(String pathTest) throws Exception{
		facade.setPathTest(pathTest);
	}
	
	public void setPathNew(String pathNew){
		facade.setPathCodeNew(pathNew);
	}
	
	public void addTechnique(String techniqueName) throws Exception{
		facade.addTechnique(techniqueName);
	}
	
	public void removeTechnique(String techniqueName) throws Exception{
		facade.removeTechnique(techniqueName);
	}
	
	public String getAddedTechniquesNames(){
		return formatOutput(facade.getAddedTechniquesNames());
	}
		
	public void runInstrumentation() throws InstrumentationUnrealizedException {
		facade.runInstrumentation();
	}
	
	public boolean isInstrumented(){
		return facade.isInstrumented();
	}
	
	public void runCoverage() throws CoverageUnrealizedException, 
	InstrumentationUnrealizedException{
		facade.runCoverage();
	}


	public boolean isCovered(){
		return facade.isCovered();
	}
	
	public int getNumberOfTechniques(){
		return facade.numberOfTechniques();
	}
	
	private String formatOutput(List<String> list){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("{");
		
		int counter=0;
		for (String s : list){
			builder.append(s);
			if (counter < list.size()-1){
				builder.append(",");
				counter++;
			}
		}
		builder.append("}");
		return builder.toString();
	}

	// logic to prioritization process
	
	public int generateSuiteId(){
		return idSuite++;
	}
	
	public int generateTestId(){
		return idTest++;
	}
	
	public int generateClassCodeId(){
		return idClassCode++;
	}
	
	public int generateMethodId(){
		return idMethod++;
	}
	
	public int generateStatementId(){
		return idStatement++;
	}

	
	
}
