package util;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class check if all files difined into file Settings.java exist.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class SettingsTest {

	File file ;
	
	@Before
	public void setUp(){
		file = null;
	}
	
	@After
	public void setDown(){
		file = null;		
	}
	

	@Test
	public void testAppPaths(){
		file = new File(Settings.APP);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_CODE);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_LIB);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_TEST);
		
		assertTrue(file.exists());
	}
	
}
