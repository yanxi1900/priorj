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

import exception.DuplicateProjectNameException;
import exception.EmptyPriorJProjectNameException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import project.JUnitVersionEnum;
import project.PriorJProject;
import project.PriorJProjectManager;

import util.FileManager;
import util.PathTo;


/**
 * This class is a controller to manager project an files.
 * 
 * @author Samuel T. C. Santoss
 * @version 1.0
 *
 */
public class ProjectController {
	
    private PriorJProjectManager projectManager;
    
    private boolean IS_SUB_VERSION = false;
    private boolean isSaved, isChanged;

    public ProjectController(){
         projectManager = new PriorJProjectManager();  
         isSaved = false;
         isChanged = false;
    }
    public void removeProject(String nameProject){
        projectManager.deleteProject(nameProject);
    }
	
    public void removeAllProjects() {
    	if (FileManager.existFileOrDirectory(PathTo.PRIORJ_PROJECT))
    		FileManager.deleteAll(PathTo.PRIORJ_PROJECT);
    	
    	//create again the directory
    	FileManager.createDirectory(PathTo.PRIORJ_PROJECT);
	}
    
    /**
     * Get all projects names.
     * 
     * @return
     */
    public List<String> getProjectNames(){
    	List<PriorJProject> projects = getProjects();
    	
    	List<String> names = new ArrayList<String>();
    	
    	for (PriorJProject p : projects){
    		names.add(p.getName());
    	}
    	
    	return names;
    }
    
    public void setProjectPaths(String pathApp, String pathCode, String pathLib, String pathTest, String pathCodeNew) throws Exception{
       projectManager.setPaths(pathApp, pathCode, pathLib, pathTest, pathCodeNew);    
    }
    
    public void setSubVersion(boolean isSubVersion){
        IS_SUB_VERSION = isSubVersion;
    }
    
    public PriorJProjectManager getProjectManager() {
        return projectManager;
    }
    
    public List<PriorJProject> getProjects(){
        return projectManager.getProjects();
    }

    public void setProjectManager(PriorJProjectManager projectManager) {
        this.projectManager = projectManager;
    }
    /**
     * Open a project with name and version.
     * 
     * @param projectName
     * 		The project name.
     * @param versionName
     * 		The project version.
     */
    public void openProject(String projectName, String versionName){
     
        try {
            projectManager.openProject(projectName, versionName);
            projectManager.setOpenProject(projectName);            
        } catch (Exception ex) {
            System.out.println("open project error: " +    ex.getMessage());
        }
        
        setSubVersion(true);
    }
    
    /**
     * Create a new Project.
     * 
     * @param name
     * 		Project name
     * @param version
     * 		JUnit version
     * @throws Exception
     */
    public void createNewProject(String name , String version) throws Exception {

    	if (version.toLowerCase().equals("junit3"))
    		projectManager.createNewProject(name, JUnitVersionEnum.JUNIT3);
    	else if (version.toLowerCase().equals("junit4")){
    		projectManager.createNewProject(name, JUnitVersionEnum.JUNIT4);
    	}
    }
    
    /**
     * Search if exist a project with the informed name.
     * 
     * @param projectName
     * 		The project name.
     * @return
     * 		true or false.
     */
    public boolean searchProject(String projectName){
    	return projectManager.searchProject(projectName);
    }
    
    public PriorJProject getOpenProject(){
        try {
            return projectManager.getOpenProject();
        } catch (Exception ex) {
            System.err.println("Open Project: " + ex.getMessage());
            return null;
        }
    }
    
    public void closeProject(){
        try {
            if(!isSaved){
                if (isSubVersion()){
                    if (isChanged)
                        projectManager.commitProjectVersion();
                }
                else
                    projectManager.commit();
            }
            
            projectManager.closeProject();
        } catch (Exception ex) {
            System.err.println("Close Project"+ ex.getMessage());
        }
    }
    

    public void saveProject(){
        if (isSubVersion())
            commitVersion();
         else
            commitProject();
    }
    
    public void commitVersion(){
         projectManager.commitProjectVersion();
         isSaved = true;
    }
    
    public void commitProject(){
        try {
            projectManager.commit();
            isSaved = true;
        } catch (Exception ex) {
            System.err.println("Commit Project " + ex.getMessage());
        }
    }
    

    public boolean hasOpenProject(){
        try {
            return projectManager.hasOpenProject();
        } catch (Exception ex) {
            System.err.println("Has Open Project error: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean isSubVersion(){
        return IS_SUB_VERSION;
    }
    
    public void setIsChanged(boolean changed){
        isChanged = changed;
    }
    
    public boolean isSaved(){
        return isSaved;
    }
    
    public boolean isChanged(){
        return isChanged;
    }
    
    private String parser(String path){
        
        if (path.contains("\\")){
            path = path.replace("\\", PathTo.SEPARATOR);
        }
        else if (path.contains("/"))
            path = path.replace("/", PathTo.SEPARATOR);
        
        return path;
    }
    
    public JUnitVersionEnum junitVersion(){
        return getOpenProject().getVersion();
    }
     
    public String[] getPathsOpenProject(){
        PriorJProject p = getOpenProject();
        
        String pathNew = p.getPathCodeNew();
        String pathApp = p.getPathApp();
        String pathCode = p.getPathCode();
        String pathLib = p.getPathLib();
        String pathTests = p.getPathTest();
        
        String [] paths = {pathApp, pathCode, pathLib, pathTests, pathNew};
        
        return paths;
    }
    
    public List<String> openJavaPrioritizedSuites(){
        try{
             String local = PathTo.USER_DIR;
             String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR+"suites"+PathTo.SEPARATOR;
             
             List<String> suites = projectManager.openAllFiles(local+directory);
             
             return  suites;
         }
         catch(Exception ex){
             System.out.println("Open Java Prioritized Suite Code error: " + ex.getMessage());
             return new ArrayList<String>();
         }
     }
    
    public List<String> openTechniquesNames(){
        String local = PathTo.USER_DIR;
        
        String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR+"order"+PathTo.SEPARATOR;

        try {            
            List<String> techniqueNames = projectManager.getFilesListNames(local+directory);
            return techniqueNames;
            
        } catch (Exception ex) {
            System.err.println("Open Techniques Names "+ ex.getMessage());
            return new ArrayList<String>();
        }           
    }

    public List<String> openSuitesNames(){
        
        try {
            String local = PathTo.USER_DIR;
            String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR+"suites"+PathTo.SEPARATOR;
            List<String> listNames = projectManager.getFilesListNames(local+directory);
            
            return listNames;
        } catch (Exception ex) {
            System.err.println("Open suites Names error: " + ex.getMessage());
            return new ArrayList<String>();
        }
    }

    public List<String> openOrder(){
       
       try {
           String local = PathTo.USER_DIR;
           String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR+"order"+PathTo.SEPARATOR;
           
           List<String> order = projectManager.openAllFiles(local+directory);
       
           return order;
           
       } catch (Exception ex) {
           System.err.println("Open execution order error:"  + ex.getMessage());
           return new ArrayList<String>();
       }
           
    }
    
    public List<String> openPrioritizedSuites(){
        try{
               String local = PathTo.USER_DIR;
               String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR+"suites"+PathTo.SEPARATOR;
               
               List<String> suites = projectManager.openAllFiles(local+directory);
               
               return  suites;
           }
           catch(Exception ex){
               System.err.println("Prioritized Suite Code: " + ex.getMessage());
               return new ArrayList<String>();
           }
       }
    
      public List<List<String>> openAllPrioritizedTestSuites(String path){
    	return projectManager.openAllPrioritizedTestSuites(path);
    }
	
    
}
