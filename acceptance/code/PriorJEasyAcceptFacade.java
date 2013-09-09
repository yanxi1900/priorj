package code;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;

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

	public void removeProject(String projectName){
		facade.removeProject(projectName);
	}
	
	public int numberOfProjects(){
		return facade.numberOfProjects();
	}
	
	public String getFieldProject(String field){
		return "";
	}
	
}
