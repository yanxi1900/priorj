package util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.WriteAbortedException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class is user to verify the class <code>SubstituiStringArquivo</code>.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class SubstituiStringArquivoTest {

	SubstituiStringArquivo ssa;
	
	private String path;
	private File file;
	
	private final String local = System.getProperty("user.dir"); 
	private final String separator = System.getProperty("file.separator");
	private final String content = separator + "src" + separator + "test" + separator + "resources"+separator;
	private String filename = "";
	private String folder;
	
	@Before
	public void setUp() throws Exception {
		ssa = new SubstituiStringArquivo();
	}

	@After
	public void tearDown() throws Exception {
		ssa = null;
	}

	@Test
	public void testReplaceFileContent() throws Exception {
		folder = "files" + separator;
		filename = "replace.txt";
		
		String pathOrigin = local + content  + folder;
		
		String pathDestination = local + content + "copy" + separator; 
		
		ManagerFiles.copyFileAll(pathOrigin, pathDestination);
		
		pathDestination = pathDestination + filename;
		
		file = new File(pathDestination);
		
		System.out.println(pathDestination);
		
		assertTrue(file.exists());
		
		String code = "abc.code.com";
		String test = "abc.test.com";
		String pointcut = "(..*(test)..*../*.java)";
		
		ssa.setPath(file, code, test, pointcut);
		
		String content = ReadFile.read(pathDestination);
		
		assertTrue(content.contains(code));
		assertTrue(content.contains(test));
		assertTrue(content.contains(pointcut));
	
	}

}
