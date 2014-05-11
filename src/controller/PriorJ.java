package controller;
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
