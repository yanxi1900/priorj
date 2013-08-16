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
	public void testApplicationPaths(){
		file = new File(Settings.APP);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_CODE);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_LIB);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_TEST);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_CODE_NEW);
		
		assertTrue(file.exists());
		
		
		file = new File(Settings.APP_AVL);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_AVL_CODE);
		
		assertTrue(file.exists());
		
		file = new File(Settings.APP_AVL_TEST);
		
		assertTrue(file.exists());

		file = new File(Settings.INSTRUMENT_TESTS);
		
		assertTrue(file.exists());
		
		file = new File(Settings.INSTRUMENT_BLOCKS);
		
		assertTrue(file.exists());
		
		
	}
	
	@Test
	public void testPathResourceFolderFiles(){
		file = new File(Settings.RESOURCES_FILES);
		
		assertTrue(file.isDirectory());
		
	}
	
	@Test
	public void testPathResourceTxtFile(){
		String filename = "TMC.txt";
		String path = Settings.RESOURCES_FILES + Settings.SEPARATOR + filename;
		
		file = new File(path);
		
		assertTrue(file.exists());
	}
	
	
}
