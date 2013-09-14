package project;

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
import java.util.Calendar;
import java.util.List;

import util.FileManager;
import util.PathTo;

/**
 * This class does the implementation of ProjectManager interface.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJProjectManager implements ProjectManager  {
    
    private List<PriorJProject> projects;
    private PriorJProjectDAO repository;
    private PriorJProject openProject;
    private final String separator = System.getProperty("file.separator");
    
    /**
     * Default constructor.
     */
    public PriorJProjectManager(){
        projects = new ArrayList<PriorJProject>();
        repository = new PriorJProjectDAO();
        init();
    }
    /**
     * Constructor		
     * @param projectName
     * 			The Project name.
     * @param projectVersion
     * 			The PriorJ Project name.
     */
    public PriorJProjectManager(String projectName, String projectVersion){
        this();
        this.openProject = getProjectName(projectName);
    }
    
    /**
     * Initialization for this class.
     */
    private void init(){
        if (!repository.checkPriorJProjectDirectory()){
            repository.createPriorJProjectDirectory();
        }
        
        if ( repository.checkProjectFile()){
            try {
                projects = repository.readFile();
                
                openProject = getOpenProject();
                
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        String local  = System.getProperty("user.dir");
        String directory = separator+"report"+separator;
        
        if (!FileManager.existFileOrDirectory(local+directory)){
            FileManager.createDirectory(local+directory);
        }
       
    }
    /**
     * Verify if exist project file, with information 
     * about projects.
     * 
     * @return
     * 		True or False.
     */
    public boolean checkProjectFile(){
        return repository.checkProjectFile();
    }
    
    /**
     * Get information about the open project.
     * 
     * @return
     * 		A String with name and version project.
     * 
     * @throws Exception
     * 		Input/Output problems.
     */
    public String getProjectInfo() throws Exception{
        PriorJProject p = getOpenProject();
        
        String name = p.getName();
        String version = p.getVersion().getName();
        
        return name + " - " + version;
    }
    
    
    /**
     * Create a new PriorJ Project.
     *  
     * @param name 
     * 		The PriorJ Project name.
     * @param version  
     * 		The JUnit version.
     * @throws EmptyPriorJProjectNameException 
     */
    public void createNewProject(String name, JUnitVersionEnum version) throws EmptyPriorJProjectNameException, DuplicateProjectNameException {
            
        if (searchProject(name))
            throw new DuplicateProjectNameException();
        
        Calendar today = Calendar.getInstance();
        openProject = new PriorJProject(name, today, version);
        
        //check repository file
       if ( repository.checkProjectFile()){
            try {
                projects = repository.readFile();
                
                addNewProject(openProject);
                
                //set file
                repository.writeFile(projects);
                repository.createDirectory(name);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        else{
            addNewProject(openProject);
            repository.writeFile(projects);
            repository.createDirectory(name);
        }
       
       
    }
    
    /**
     * Verify if the project PriorJ projects exist.
     * 
     * if not exits , than this directory is created.
     */
    private void checkProjectDirectory(){
     if ( !repository.checkPriorJProjectDirectory() ){
            repository.createPriorJProjectDirectory();
        }
    }
    /**
     * Add a new project.
     * 
     * @param project
     * 		The project to be added.
     */
    private void addNewProject(PriorJProject project){
        projects.add(project);   
    }
    
    public void openProject(String projectName, String version){
            
        openProject = getProjectName(projectName);
        setOpenProject(projectName);
        
        repository.setOpenProject(projectName, version);
                
        //to write there is a open project...
        repository.writeFile(projects);
        
        //do backup files
        String localOrigem = PathTo.PRIORJ_PROJECT + PathTo.SEPARATOR+ projectName + PathTo.SEPARATOR + version;
        
        String localDestination = PathTo.REPORT;
        
        FileManager.copyFileAll(localOrigem, localDestination);
        
    }
    
    public boolean searchProject(String projectName) {
        return repository.find(projectName);
    }

    public void deleteProject(String projectName) {
        
        int indexToRemove=0;
        if ( searchProject(projectName)){
            for (PriorJProject p : projects){
                if (p.getName().equals(projectName)){
                    break;
                }
                indexToRemove++;
            }
        }
           
        projects.remove(indexToRemove);
        repository.deleteProject(projectName);
        
        //write to file the modifications in the projects list.
        repository.writeFile(projects);
    }
    /**
     * Write the project information into hard disc.
     * 
     * @throws Exception
     * 			Input/Output problems.
     */
    public void commit() throws Exception{
        openProject = getOpenProject();
        
        repository.commitProject(openProject.getName());
    }
    
    /**
     * Write into the hard disc a new version of a
     * project existent.
     * 
     * @param projectName
     * 			The project name.
     */
    public void commitProjectVersion(String projectName){
        repository.commitProject(projectName);
    }
    
    public void commitProjectVersion(){
        String projectName = openProject.getName();
        
        repository.commitProject(projectName);
    }
    
   
    public void setOpenProject(String projectName){
        for (PriorJProject  p : projects){
            if (p.getName().equals(projectName)){
                p.setOpenProject();
            }
        }
    }
    
    public boolean hasOpenProject() throws Exception {
        projects = repository.readFile();
        for (PriorJProject  p : projects){
            if (p.isOpenProject()){
               return true;
            }
        }
        return false;
    }
    
    public int totalProjects(){
        try {
            projects = repository.readFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return projects.size();
    }
    
    public PriorJProject getOpenProject() throws Exception{
        
        if (repository.checkProjectFile()){
            
            projects = repository.readFile();
        
            for (PriorJProject  p : projects){
                if (p.isOpenProject()){
                    return p;
                }
            }  
        }
        return null;
    }
    
     public void updateProject(PriorJProject project) throws Exception{
        
        if (repository.checkProjectFile()){
            
            projects = repository.readFile();
        
            for (int i=0; i<projects.size();i++){
                if (projects.get(i).equals(project)){
                    projects.set(i, project);
                    repository.writeFile(projects);
                    return;
                }
            }  
            
           
        }
       
    }
   
     
    public List<PriorJProject> getProjects(){
        return projects;
    }
     
    public PriorJProject getProjectName(String projectName){
        for (PriorJProject  p : projects){
            if (p.getName().equals(projectName)){
                return p;
            }
        }
        return null;
    }
   
    
    public void closeProject() throws Exception{
        
        if (repository.checkProjectFile()){
            projects = repository.readFile();
        
            for (PriorJProject  p : projects){
                if (p.isOpenProject()){
                    p.setCloseProject();
                }
            }
        
            repository.writeFile(projects);
        }
    }
    
    public List<String> listProjects() {
        return repository.listDirectoryNames();
    }
    
    public String openFileProject(String filename) throws Exception{
        return repository.openProjectFile(filename); 
    }
    
    
    public List<String> openAllFiles(String directoryName) throws Exception{
        return  repository.openAllFiles(directoryName);
    }
    
    
    //paths informations
    public void setPathApp(String pathApp) throws Exception{
        getOpenProject().setPathApp(pathApp);
    }
    
    public void setPathCode(String pathCode) throws Exception{
        getOpenProject().setPathCode(pathCode);
    }
    
    public void setPathLib(String pathLib) throws Exception{
        getOpenProject().setPathLib(pathLib);
    }
    
    public void setPathTest(String pathTest) throws Exception{
        getOpenProject().setPathTest(pathTest);
    }
    
    public void setPathOld(String pathOld) throws Exception{
        getOpenProject().setPathCodeNew(pathOld);
    }
    
    
    public String setPathApp() throws Exception{
        return getOpenProject().getPathApp();
    }
    
    public String getPathApp() throws Exception{
        return getOpenProject().getPathApp();
    }
    
    public String getPathCode() throws Exception{
        return getOpenProject().getPathCode();
    }
    
    public String getPathLib() throws Exception{
        return getOpenProject().getPathLib();
    }
    
    public String  getPathTest() throws Exception{
        return getOpenProject().getPathTest();
    }
    
    public String getPathNewCode() throws Exception{
        return getOpenProject().getPathCodeNew();
    }
        
    public void setPaths(String app, String code, String lib, String test, String codenew) throws Exception{
    
         PriorJProject p = getOpenProject();
         
         p.setPathApp(app);
         p.setPathCode(code);
         p.setPathLib(lib);
         p.setPathTest(test);
         p.setPathCodeNew(codenew);
         
         updateProject(p);
    }
    
    
    public List<String> getFilesListNames(String path) throws Exception{
        
        List<String> fileNames = FileManager.listFilesNames(path);
        
        return fileNames;
    }
    
     public List<List<String>> openAllPrioritizedTestSuites(String path){
    	return repository.openAllPrioritizedTestSuites(path);
    }
    
}
