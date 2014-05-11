package controller;

import java.util.ArrayList;
import java.util.List;

import technique.TechniqueCreator;

import com.java.io.JavaIO;

/**
 * Class PriorJ priorization of JUnit Test Cases. 
 *  
 * @author Samuel T. C. Santos
 *
 */
public class PriorJ {

	private static PriorJ instance;
	private static List<Integer> techniques;
	
	String localbase;
	
	public static PriorJ getInstance(){
		if (PriorJ.instance == null){
			techniques = new ArrayList<Integer>();
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

	/**
	 * Adding the selected Technique by user.
	 * 
	 * @param typeOfTechnique
	 */
	public void addTechnique(int typeOfTechnique) {
		if (typeOfTechnique == TechniqueCreator.ADDITIONAL_METHOD_COVERAGE){
			techniques.add(TechniqueCreator.ADDITIONAL_METHOD_COVERAGE);
		}
		else if(typeOfTechnique == TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE){
			techniques.add(TechniqueCreator.ADDITIONAL_STATEMENT_COVERAGE);
		}
		else if (typeOfTechnique == TechniqueCreator.CHANGED_BLOCKS){
			techniques.add(TechniqueCreator.CHANGED_BLOCKS);
		}
		else if (typeOfTechnique == TechniqueCreator.RANDOM){
			techniques.add(TechniqueCreator.RANDOM);
		}
		else if (typeOfTechnique == TechniqueCreator.TOTAL_METHOD_COVERAGE){
			techniques.add(TechniqueCreator.TOTAL_METHOD_COVERAGE);
		}
		else if (typeOfTechnique == TechniqueCreator.TOTAL_STATEMENT_COVERAGE){
			techniques.add(TechniqueCreator.TOTAL_STATEMENT_COVERAGE);
		}
		else{
			throw new IllegalArgumentException("Invalid Technique Type!");
		}
	}
	
	/**
	 * Removing a selected technique.
	 * 
	 * @param typeOfTechnique
	 */
	public void removeTechnique(int typeOfTechnique){
		if (techniques.contains(typeOfTechnique)){
			int index = techniques.indexOf(typeOfTechnique);
			techniques.remove(index);
		}
	}

	/**
	 * The list of selected Techniques Types.
	 * 
	 * @return
	 */
	public List<Integer> getTechniques() {
		return techniques;
	}

	/**
	 * Creating the local base folder.
	 * 
	 * @param path
	 * @throws Exception
	 */
	private void createLocalbase(String path) throws Exception {
		if (path != null && !path.isEmpty()){
			JavaIO.createFolder(path);
		}
		else{
			throw new Exception("Invalid Path!");
		}
	}

}
