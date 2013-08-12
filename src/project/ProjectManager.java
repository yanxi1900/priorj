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
