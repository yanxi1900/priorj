package controller;
/**
 * Class PriorJ priorization of JUnit Test Cases. 
 *  
 * @author Samuel T. C. Santos
 *
 */
public class PriorJ {

	String localbase;
	
	/**
	 * Set the location where the artifacts should be saved.
	 * 
	 * @param localbase
	 */
	public void setLocalBasePath(String localbase) {
		this.localbase = localbase;
	}

	/**
	 * Get the location where the artifacts are saved.
	 * 
	 * @return
	 */
	public String getLocalBasePath() {
		return localbase;
	}

}
