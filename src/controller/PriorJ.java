package controller;

import com.java.io.JavaIO;

/**
 * Class PriorJ priorization of JUnit Test Cases. 
 *  
 * @author Samuel T. C. Santos
 *
 */
public class PriorJ {

	private static PriorJ instance;
	
	String localbase;
	
	public static PriorJ getInstance(){
		if (PriorJ.instance == null){
			PriorJ.instance = new PriorJ();
		}
		return PriorJ.instance;
	}
	
	/**
	 * Set the location where the artifacts should be saved.
	 * 
	 * @param localbase
	 * @throws Exception 
	 */
	public void setLocalBasePath(String localbase) throws Exception {
		this.localbase = localbase;
		createLocalbase(localbase);
	}

	/**
	 * Get the location where the artifacts are saved.
	 * 
	 * @return
	 */
	public String getLocalBasePath() {
		return localbase;
	}
	
	private void createLocalbase(String path) throws Exception {
		if (path != null && !path.isEmpty()){
			JavaIO.createFolder(path);
		}
		else{
			throw new Exception("Invalid Path!");
		}
	}

}
