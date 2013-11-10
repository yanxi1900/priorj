package report;

import static org.junit.Assert.*;
import japa.parser.ParseException;
import japa.parser.ast.body.MethodDeclaration;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

/**
 * This class is an test to class under test <code> ReplaceMethodMain</code>.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class ReplaceMethodMainTest {

	private ReplaceMethodMain replace;
	
	private String pathfile = "";
	
	
	@Before 
	public void setUp() throws FileNotFoundException, ParseException{
		pathfile = PathTo.RESOURCES_FILES + PathTo.SEPARATOR + "Main.java";
		
		replace = new ReplaceMethodMain(pathfile);
	}
	
	@After
	public void tearDown(){
		pathfile = "";
		replace = null;
	}
	
	
	@Test
	public void testLocateMainMethod() {
		
		MethodDeclaration methodDec = replace.findMainMethod();
		
		assertEquals("name should be main!", "main",methodDec.getName());
	}

	
	
}
