package report;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import util.FileManager;
import util.PathTo;
import util.ReadFile;

/**
 * This class do an update in the generated test suite.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class UpdateGeneratedTestSuite {
	
	/**
	 * This field allow to validate the conditions where are
	 * possible do an update in the test suite.
	 */
	public boolean active;

	public UpdateGeneratedTestSuite(){
		active = false;
	}
	
	/**
	 * This method verify if exist generated Test Suite.
	 * 
	 * @return
	 * 		true or false.
	 * 
	 * @throws Exception
	 * 		directory not found.
	 */
	public boolean existGeneratedTestSuiteCode() throws Exception{
		List<String> files = FileManager.listFilesNames(PathTo.SUITES);
		
		return files.size()!=0;
	}
	
	/**
	 * This method detect all suite existent into suites directory.
	 * 
	 * @return
	 * 		A list with suites names.
	 */
	public List<String> detectAllSuites(){
		try {
			return FileManager.listFilesNames(PathTo.SUITES);
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * This method get the prioritized order to generate a new 
	 * Test Suite.
	 * 
	 * @param fileName
	 * 		The order filename.
	 * 
	 * @return
	 * 		A list of test case in prioritized order.
	 */
	public List<String> detectOrder(String fileName){
		
		if (fileName.endsWith(".java"))
			fileName = fileName.replace(".java", ".txt");
		
		String path = PathTo.ORDER + PathTo.SEPARATOR + fileName;
				
		return ReadFile.readFileAndReturnList(path);
	}
	
	/**
	 * Verify the status to flag active.
	 * 
	 * @return
	 * 		true or false.
	 */
	public boolean isActived(){
		return active;
	}
	
	/**
	 * This method change the status of the active controller.
	 * 
	 * @param status
	 * 		true or false
	 */
	public void active(boolean status){
		active = status;
	}
}
