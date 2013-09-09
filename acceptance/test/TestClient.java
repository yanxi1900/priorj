package test;

import java.util.ArrayList;

import easyaccept.EasyAcceptFacade;

import java.util.List;

import code.PriorJEasyAcceptFacade;

public class TestClient {

	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		String separator = System.getProperty("file.separator");
		
		String path = "acceptance-test" + separator; 
		
		// Put the testScript1 file into the "teste scripts" list
		files.add(path + "US01.txt");
		
		PriorJEasyAcceptFacade yourTestFacade = new PriorJEasyAcceptFacade();

		// Instantiate EasyAccept facade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(yourTestFacade, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		
	}

}