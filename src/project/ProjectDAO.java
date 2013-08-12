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
import java.util.List;

/**
 * This Interface manager all operations about 
 * project manipulations.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public interface ProjectDAO {
    
    /**
     * Get the project List,
     * 
     * @return
     * 		A list of <code>PriorJProject</code>
     * 
     * @throws Exception 
     * 			I/O Problems in the files. 
     */
    public List<PriorJProject> readFile() throws Exception;
    
    /**
     * List all files inside the project directory.
     *  
     * @return a array of File.
     */
    public File[] listFiles();
    
    /**
     * Write to hard disc a list of projects.
     * 
     * @param projects a list of projects.
     */
    public void writeFile(List<PriorJProject> projects);
    
    /**
     * Remove a file.
     */
    public void removePriorJProjectFile();
       
    /**
     * Create a new Directory to store all PriorJ projects.
     * 
     */
    public void createPriorJProjectDirectory();
    
    /**
     * Create a directory with informed name.
     * 
     * @param directoryName
     * 			The directory name.
     */
    public void createDirectory(String directoryName);
    
    /**
     * Remove a directory with informed name.
     *  
     * @param directoryName
     * 			The directory name.
     */
    public void removeDirectory(String directoryName);
    
    /**
     * Verify if exist a specific file.
     *  
     * @param fileName
     * 			The file name.
     * @return
     * 			True or False
     */
    public boolean checkFile(String fileName);
    
}
