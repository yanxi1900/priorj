package controller;

import java.util.List;

import com.java.io.JavaIO;

/**
 * Data Manager class.
 *  
 * @author Samuel T. C. Santos
 *
 */
public class DataManager {
	
	private static String localbase;
	private static String projectFolder;
	private static String versionFolder;
	private static final String slash = JavaIO.SEPARATOR;
		
	/**
	 * Creating the local base folder.
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void createLocalbase(String path) throws Exception {
		if (path != null && !path.isEmpty()){
			DataManager.localbase = path;
			JavaIO.createFolder(path);
		}
		else{
			throw new Exception("Invalid Path!");
		}
	}
	
	/**
	 * This method save Coverage Data to XML file.
	 * 
	 * @param localPath
	 * @param fileName
	 * @param allSuites
	 */
	public static void saveCoverageData(String localPath, String fileName,	@SuppressWarnings("rawtypes") List<List> allSuites) {
		JavaIO.saveObjectToXML(localPath, fileName, allSuites, false);
	}
	/**
	 * Opening the coverage file and retrieve coverage data.
	 * 
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<List> openCoverageData(String filePath) {
		@SuppressWarnings("unchecked")
		List<List> coverage = (List<List>) JavaIO.getObjectFromXML(filePath);
		return coverage;
	}
	
	/**
	 * Create a sub folder to save prioritized version to same project.
	 * 
	 * @param projectFolder
	 * @param versionFolder
	 * @throws Exception 
	 */
	public static void createFolderVersion(String projectFolder, String versionFolder) throws Exception {
		if (DataManager.localbase.isEmpty())
			throw new Exception("Set local base path!");
		if ( validate(projectFolder) && validate(versionFolder)){
			DataManager.projectFolder = projectFolder;
			DataManager.versionFolder  = versionFolder;
			JavaIO.createFolder(DataManager.localbase+slash+projectFolder+slash+versionFolder);
		}
	}
	
	
	/**
	 * This method create a project folder inside the local base.
	 * 
	 * @throws Exception
	 */
	public static void createProjectFolder(String folderName) throws Exception{
		if(DataManager.localbase.isEmpty())
			throw new Exception("Set local base path!");
		
		DataManager.projectFolder = folderName;
		JavaIO.createFolder(DataManager.localbase+slash+folderName);
	}
	
	public static String getProjectFolderName(){
		return DataManager.projectFolder;
	}
	
	/**
	 * Get the location where the artifacts are saved.
	 * 
	 * @return
	 */
	public static String getLocalBasePath() {
		return DataManager.localbase;
	}
	
	/**
	 * Basic validation to a system path!
	 * 
	 * @param path
	 * @return
	 */
	private static boolean validate(String path){
		return path != null && !path.isEmpty();
	}
	
	/**
	 * This method save 
	 * @param string
	 * @param report
	 */
	public static void save(String filename, String content) {
		JavaIO.createTextFile(DataManager.localbase+slash+DataManager.projectFolder+ slash +DataManager.versionFolder, filename, content, false);
	}

	public static String openFile(String filePath) {
		return JavaIO.openTextFile(filePath);
	}
}
