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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import dao.XStreamRead;
import dao.XStreamWrite;

import java.util.LinkedList;

import util.CopyFile;
import util.FileManager;
import util.ReadFile;
import util.PathTo;


/**
 * This class implements the interface PriorJDAO
 * which provide persistence to information about
 * the PriorJ Projects.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJProjectDAO implements ProjectDAO {

    private XStreamWrite writer;
    private String local = PathTo.USER_DIR;
    
    private String directory = PathTo.SEPARATOR + "PriorJProjects"+PathTo.SEPARATOR;
    private String file = "info-projects";
    private String directoryName = PathTo.SEPARATOR+"PriorJProjects";
        
    private XStreamRead reader;

    /**
     * Default constructor.
     */
    public PriorJProjectDAO(){
        local = System.getProperty("user.dir");
        directory = PathTo.SEPARATOR+"PriorJProjects"+PathTo.SEPARATOR;
        file = "info-projects";
        directoryName = PathTo.SEPARATOR+"PriorJProjects";
    }
    
    
    /**
     * Create the directory project with name PriorJProject.
     */
    public void createPriorJProjectDirectory() {
        
    	File file = new File(local+directory);
    	file.mkdir();

    }

    /**
     * Create a sub directory into PriorJProject directory.
     * 
     * @param directoryName
     * 			The directory name 
     */
    public void createDirectory(String directoryName) {
    	
        String path = PathTo.USER_DIR;
        
        path += PathTo.SEPARATOR+"PriorJProjects"+PathTo.SEPARATOR;
        
      
        File file = new File(path + directoryName);
        
        file.mkdir();
       
    }
    /**
     * Create a XML file with information about all projects.
     *  
     */
    public void createProjectFile(){
        File f = new File(local+directory+file+".xml");
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void removeDirectory(String directoryName) {
        String path = PathTo.USER_DIR;
        
        path += PathTo.SEPARATOR + "PriorJProjects"+PathTo.SEPARATOR;
       
        File file = new File(path+directoryName);
        
        deleteAll(file);
    }
    
    /**
     * Delete a project by project name.
     * 
     * @param projetName
     * 		 The project name.
     */
    public void deleteProject(String projetName){
        if (find(projetName)){
            removeDirectory(projetName);
        }
    }
    
    /**
     * Check if a sub directory exists inside PriorJProject.
     * @param fileName
     * 		 The name of sub directory.
     * @return  
     * 		 True or False.
     */
    public boolean checkFile(String fileName) {
        String path = System.getProperty("user.dir");
        
        //new directory
        String directoryName = PathTo.SEPARATOR+"PriorJProjects"+PathTo.SEPARATOR;
        
        File f = new File(path+directoryName+fileName);
 
        return f.exists();
    }
    
    /**
     * Verify if exist the file with information about project.
     * 
     * @return
     * 			True or False
     */
    public boolean checkProjectFile(){
        return checkFile(file + ".xml");
    }
    
    /**
     * Check if directory project exists.
     * 
     * @return 
     * 			True or False.
     */
    public boolean checkPriorJProjectDirectory(){
        String path = System.getProperty("user.dir");
        
        String directoryName = PathTo.SEPARATOR + "PriorJProjects" +PathTo.SEPARATOR;
        
        File f = new File(path+directoryName);
 
        return f.exists();
    } 

    /**
     * This method open the file with information about all project
     * and return a project list.
     */
    public List<PriorJProject> readFile(){
        
        reader = new XStreamRead(local+ directory + file);
        
        try {
            return (List<PriorJProject>)reader.read();
            
        } catch (Exception ex) {
            return new ArrayList<PriorJProject>();
        }
    }
    
    /**
     * List all projects directory from PriorJProject.
     * 
     * @return 
     * 			a array of File. 
     */
    public File[] listFiles() {
        File f = new File (local+directory);
        return f.listFiles();
    }
    
    /**
     * List all project directories. 
     * 
     * @return  
     * 			A list with project names.
     */
    public List<String> listDirectoryNames(){
        List<String> directories = new ArrayList<String>();
        File []files = listFiles();
        
        for (File f : files)
            if (f.isDirectory())
                directories.add(f.getName());
        
        return directories;
    }
    
    /**
     * Write a project list in the file.
     * 
     */
    public void writeFile(List<PriorJProject> projects) {
        writer = new XStreamWrite(local+directory+file);
        
        if (!projects.isEmpty()){
            writer.write(projects);
        }
        else{
            removePriorJProjectFile();
        }
            
    }

    public void removePriorJProjectFile() {
        File f = new File(local+ directory + file + ".xml");
        
        f.delete();
    }

    
    /**
     * Search file by file name.
     * 
     * @return 
     * 			True or False
     */
    public boolean find(String fileName){
        File [] files = listFiles();
        
        for(File f : files)
            if (f.getName().equals(fileName))
                return true;
        return false;
    }

    /**
     * Copy all files from origin to destination.
     * 
     * @param origin 
     *        The  origin directory.
     * @param destination
     * 		  The destination directory .
     */
    public void moveAllFiles(String origin, String destination){
        
        try {
            File orig = new File(origin);
            File dest = new File(destination);
        
            CopyFile.copyAll(orig, dest, true);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Open project by name.
     * 
     * @param projectName  
     * 			The project name.
     */
    public void setOpenProject(String projectName){
        if (find(projectName)){
            String origin = local+directory + projectName + PathTo.SEPARATOR;
            String destination = local + PathTo.SEPARATOR+"report"+PathTo.SEPARATOR;
       
            File f = new File(destination);
            f.delete();
        
            moveAllFiles(origin, destination);
        }
    }
    
    /**
     * Open a selected project version.
     * 
     * @param projectName
     * 				The project name.
     * @param version 
     * 				The version name of project.
     */
    public void setOpenProject(String projectName, String version){
        if (find(projectName)){
            String origin = local+directory + projectName + PathTo.SEPARATOR + version +PathTo.SEPARATOR;
            String destination = local + PathTo.SEPARATOR +"report"+PathTo.SEPARATOR;
       
            File f = new File(destination);
            deleteAll(f);
        
            moveAllFiles(origin, destination);

        }
    }  
    
    /**
     * Set a new project inside the projects directory.
     * 
     * @param projectName
     * 			The project name.
     */
    public void commitProject(String projectName){
            if (find(projectName)){
                String destination = local+directory + projectName + PathTo.SEPARATOR;
                
                String origin = local + PathTo.SEPARATOR +"report"+PathTo.SEPARATOR;
                
                //list directory destination...
                File f = new File(destination);
                File [] files = f.listFiles();
        
                String versionName = projectName + " version-" + (files.length+1) + PathTo.SEPARATOR;
                destination += versionName;
                
                moveAllFiles(origin, destination);
            }
            else{
                System.out.println("Project: " +projectName + " not found!" );
            }
    }
    
    /**
     * Delete the directory destination.
     * 
     * @param destination
     * 			The directory name.
     */
    
    public void deleteAll(File file){
        FileManager.deleteAll(file);       
    }
    
    /**
     * Open a project with specific name.
     * 
     * @param filename
     * 			The file name.
     * @return
     * 			A string with content file.
     * 
     * @throws Exception
     * 			Input/Output problems.
     */
    public String openProjectFile(String filename) throws Exception{
        
        String directory = PathTo.SEPARATOR + "report"+PathTo.SEPARATOR;
        
        String contentFile =  ReadFile.read(local + directory + filename);
        
        return contentFile;
    }
    
    /**
     * Open every file and merge the contend inside an String.
     * 
     * @param pathDirectory
     * 			The directory path
     * 
     * @return
     * 			The content of every files.
     * 
     * @throws Exception
     * 			Input/Output problems or access file.
     */
    public List<String> openAllFiles(String pathDirectory) throws Exception{
        
        File f = new File(pathDirectory);
        List<String> contents = new ArrayList<String>();
        
         if (f.exists()){
            
            File []files = f.listFiles();
        
            if(files.length>0){
                String content="";
                for (int i=0; i< files.length;i++){
                    content = ReadFile.read(pathDirectory+files[i].getName());
                    contents.add(content);
                }
                
            }
        }
                
        return contents;
    }
       
    /**
     * This method list the content of files order.
     * 
     * @param path
     * 		The local where is the order files.
     * 
     * @return
     * 		A list of list with test cases names.
     */
    public List<List<String>> openAllPrioritizedTestSuites(String path){
    	
    	List<List<String>> lists = new LinkedList<List<String>>();
    	
    	File f = new File(path);
        List<String> contents = new ArrayList<String>();
        
         if (f.exists()){
            
            File []files = f.listFiles();
        
            if(files.length>0){
              
                for (int i=0; i< files.length;i++){
                    List<String>  list = ReadFile.readFileAndReturnList(path+files[i].getName());
                    lists.add(list);
                }
                
            }
        }
    	
    	
    	return lists;
    }
}
