package controller;
	
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.java.io.JavaIO;
	
/**
 * Testing Data Manager controller.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class DataManagerTest {
	
	@Test
	public void shouldAllowSetLocalBasePath() throws Exception{
		DataManager.createLocalbase("c:/file");
		assertEquals("c:/file", DataManager.getLocalBasePath());
	}

	@Test
	public void shouldCreateFolderInLocalBase() throws Exception{
		DataManager.createLocalbase("c:/tests/coverage/");
		assertTrue(JavaIO.exist("c:/tests/coverage/"));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithEmptyPath() throws Exception{
		DataManager.createLocalbase("");
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowExceptionWithValueNull() throws Exception{
		DataManager.createLocalbase(null);
	}

	@Test
	public void shouldCreateProjectSubVersion() throws Exception{
		DataManager.createLocalbase("c:/tests/");
		DataManager.createFolderVersion("my_project", "my_version");
		assertTrue(JavaIO.exist("c:/tests/my_project/my_version"));
	}
	
	@Test
	public void shouldCreateFolderProjectInLocalBase() throws Exception{
		DataManager.createLocalbase("c:/tests/tdd/");
		DataManager.createProjectFolder("project1");
		assertTrue(JavaIO.exist("c:/tests/tdd/project1"));
	}
	
	@Test
	public void shouldAllowTheUserGetCurrentProjectName() throws Exception{
		DataManager.createProjectFolder("project1");
		assertEquals("project1", DataManager.getProjectFolderName());		
	}
	
}
