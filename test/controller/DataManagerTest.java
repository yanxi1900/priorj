package controller;
	
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.java.io.JavaIO;
	
/**
 * Testing Data Manager controller.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class DataManagerTest {
	
	
	private String localbase;
	private String slash = JavaIO.SEPARATOR;
	
	@Before
	public void setUp(){
		if (JavaIO.isWindows()){
			localbase = "c:/file";
		}
		else{
			localbase = JavaIO.USER_DIR+JavaIO.SEPARATOR+"base";
		}
	}
	
	@Test
	public void shouldAllowSetLocalBasePath() throws Exception{
		DataManager.createLocalbase(localbase);
		assertEquals(localbase, DataManager.getLocalBasePath());
	}

	@Test
	public void shouldCreateFolderInLocalBase() throws Exception{
		DataManager.createLocalbase(localbase);
		assertTrue(JavaIO.exist(localbase));
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
		DataManager.createLocalbase(localbase);
		DataManager.createFolderVersion("p1", "my_version");
		assertTrue(JavaIO.exist(localbase+slash+"p1"+slash+"my_version"));
	}
	
	@Test
	public void shouldCreateFolderProjectInLocalBase() throws Exception{
		DataManager.createLocalbase(localbase);
		DataManager.createProjectFolder("project1");
		assertTrue(JavaIO.exist(localbase+slash+"project1"));
	}
	
	@Test
	public void shouldAllowTheUserGetCurrentProjectName() throws Exception{
		DataManager.createProjectFolder("project1");
		assertEquals("project1", DataManager.getProjectFolderName());		
	}
	
	@Test
	public void shouldGenerateTheCurrentPath() throws Exception{
		DataManager.createLocalbase(localbase);
		DataManager.createProjectFolder("current");
		DataManager.createFolderVersion("current", "folder1");
		assertEquals(localbase+slash+"current"+slash+"folder1", DataManager.getCurrentPath());
	}
	
	@Test
	public void shouldGetProjectVersion() throws Exception{
		DataManager.createLocalbase(localbase);
		DataManager.createProjectFolder("current");
		DataManager.createFolderVersion("current", "folder1");
		assertEquals("folder1", DataManager.getProjectVersion());
	}
	
	@Test
	public void shouldToSaveInSubFolder() throws Exception {
		DataManager.createLocalbase(localbase);
		DataManager.createProjectFolder("current");
		DataManager.createFolderVersion("current", "folder1");
		DataManager.save("tests.js","js", "js oooo file");
		String path = DataManager.getCurrentPath()+slash+"js"+slash+"tests.js";
		assertTrue(JavaIO.exist(path));
	}
	
	
}














