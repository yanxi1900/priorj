package core;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Settings;

/**
 * This class is a test class to class under test <code>Difference</code>
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class DifferenceTest {

	Difference difference;
	String path, pathOld;
	
	final String  separator = Settings.SEPARATOR;
	
	@Before
	public void setUp(){
		
		String local = Settings.RESOURCES_FILES + separator + "DifferenceTests" + separator; 
		
		path = local + separator+ "New" + separator +"CConstrutor.java";
		
		pathOld = local + separator+ "Old" + separator +"CConstrutor.java";
				
		difference = new Difference(path, pathOld);
	}
	
	@After
	public void tearDown(){
		difference = null;
	}
	
	@Test
	public void testDiffConstructor(){
		
		difference.diff();
		
		List<String> diffs = difference.getStatementsDiff();
				
		System.out.println(diffs);
	}
}
