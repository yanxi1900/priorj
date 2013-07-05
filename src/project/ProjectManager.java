package project;

import java.util.List;

/**
 * This interface manager the set of projects created by user.
 * 
 * An PriorJ Project has a set of information about the coverage
 * process, coverage tests and prioritization results.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public interface ProjectManager {
    
	/**
      * Project Manager Create new Project is a method which
      * create a new PriorJ Project.
      * 
      * @param name
      * 		The priorJ project name.
      * @param version
      * 		The JUnit version used in the set of test case.
      * @throws Exception
      * 		When is used an empty project name.
      */
    public void createNewProject(String name, JUnitVersionEnum version) throws Exception;
    
    /**
     * Open a PriorJ Project.
     * 
     * @param projectName
     * 			The PriorJ Project Name.
     * @param version
     * 			The PriorJ Project version.
     */
    public void openProject(String projectName, String version);
    
    /**
     * Search project by name.
     * 
     * @param projectName
     * 		The PriorJ Project Name.
     * @return
     * 		True or False
     */
    public boolean searchProject(String projectName);
    
    /**
     * Delete a project.
     *  
     * @param projectName
     * 		The project name.
     */
    public void deleteProject(String projectName);
    
    /**
     * Listing all projects created by user.
     *  
     * @return 
     * 		A list of all created projects.
     */
    public List<String> listProjects();
    
}
