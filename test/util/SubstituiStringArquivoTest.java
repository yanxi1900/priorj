package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
		
		FileManager.copyFileAll(pathOrigin, pathDestination);
		
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
