package code;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import exception.CoverageUnrealizedException;
import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;
import exception.InstrumentationUnrealizedException;

import main.PriorJFacade;

public class PriorJEasyAcceptFacade {

	PriorJFacade facade;

	public PriorJEasyAcceptFacade(){
		facade = new PriorJFacade();
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
	
}
