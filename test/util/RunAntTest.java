package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RunAntTest {
	
	private RunAnt runAnt;

	@Before
	public void setUp() throws Exception {
		runAnt = new RunAnt();
	}

	@After
	public void tearDown() throws Exception {
		runAnt = null;
	}

	
	@Test
	public void testInitialization(){
		assertEquals("", runAnt.getPathApp());
		assertEquals("", runAnt.getPathCode());
		assertEquals("", runAnt.getPathLib());
		assertEquals("", runAnt.getPathTest());	
	}
	
	@Test
	public void testRunAntRunning() {
		String app = Settings.APP;
		String code = Settings.APP_CODE.replace(app + Settings.SEPARATOR, "");
		String lib = Settings.APP_LIB.replace(app + Settings.SEPARATOR, "");
		String test = Settings.APP_TEST.replace(app + Settings.SEPARATOR, "");
		
		runAnt = new RunAnt(app,code, test,lib);
		
		runAnt.run();
	}

	
}
