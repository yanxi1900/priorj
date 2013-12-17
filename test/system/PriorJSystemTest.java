package system;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.JUnitVersionEnum;
import util.FileManager;
import util.PathTo;
import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;

public class PriorJSystemTest {

	private PriorJSystem system;
	private final String separator = System.getProperty("file.separator");
	
	@Before
	public void setUp(){
		
		system = new PriorJSystemImpl();
	}
	
	@After
	public void setDown(){
		system = null;
	}

	@Test
	public void testInitialization(){
		assertEquals("", system.getPathApp());
		assertEquals("" , system.getPathCode());
		assertEquals("" , system.getPathLib());
		assertEquals("" , system.getPathTests());
		assertEquals("" , system.getPathCodeNew());
		
		assertFalse(system.isInstrumented());
		assertFalse(system.isCovered());
		assertFalse(system.isLog());
	}
	@Test
	public void testSystemSetPathApp(){
		system.setPathApp("myproject");
		assertEquals("myproject", system.getPathApp());
	}
	
	@Test
	public void testSystemSetPathCode(){
		system.setPathCode("src");
		assertEquals("src", system.getPathCode());
	}
	@Test
	public void testSetPathLibEmpty(){
		system.setPathLib("");
		assertEquals("", system.getPathLib());
	}

	@Test
	public void testSetPathLibNonEmpty(){
		system.setPathLib("lib");
		assertEquals("lib", system.getPathLib());
	}
	
	@Test
	public void testSetPathTest(){
		system.setPathTests("test");
		assertEquals("test", system.getPathTests());
	}
	
	@Test
	public void testConstructWithPaths(){
		system = new PriorJSystemImpl("myproject", "src", "test", "","");
		assertEquals("myproject", system.getPathApp());
		assertEquals("src", system.getPathCode());
		assertEquals("lib", system.getPathLib());
		assertEquals("test", system.getPathTests());
	}
	
	@Test
	public void testRunInstrumentation() throws InstrumentationUnrealizedException{
		String app = PathTo.APP;
		
		system = new PriorJSystemImpl(app, "code", "test", "","");
		system.runInstrumentation();
		assertTrue(system.isInstrumented());
	}
	
	@Test
	public void testDoCopyFile() throws IOException{
		String app = PathTo.APP;
		String code = PathTo.APP_CODE.replace(app + separator, "");
		String lib = PathTo.APP_LIB.replace(app + separator, "");
		String test = PathTo.APP_TEST.replace(app + separator, "");
		
		system = new PriorJSystemImpl(app, code, test, lib,"");
			
		system.copyFiles();
		
		String path = PathTo.APP + separator + "AspectCoverage.aj";
		
		File f = new File(path);
		
		assertTrue(f.exists());
		
		f.delete();
		
		assertFalse(f.exists());
		
	}
	
	@Test
	public void testRemoveAspectFile() throws IOException{
		String app = PathTo.APP;
		String code = PathTo.APP_CODE.replace(app + separator, "");
		String lib = PathTo.APP_LIB.replace(app + separator, "");
		String test = PathTo.APP_TEST.replace(app + separator, "");
		
		system = new PriorJSystemImpl(app, code, test, lib,"");
			
		system.copyFiles();
		
		String path = PathTo.APP + separator +  "AspectCoverage.aj";
		
		system.deleteAspectosFile();
		
		File f = new File(path);
		
		assertFalse(f.exists());
	}
	
	
	
	@Test
	public void testRunCoverage() throws InstrumentationUnrealizedException, CoverageUnrealizedException{
		String app = PathTo.APP;
		String code = PathTo.APP_CODE.replace(app + PathTo.SEPARATOR, "");
		String lib = PathTo.APP_LIB.replace(app + PathTo.SEPARATOR, "");
		String test = PathTo.APP_TEST.replace(app + PathTo.SEPARATOR, "");
		
		system = new PriorJSystemImpl(app, code, test, lib,"");
		system.runInstrumentation();
		system.runCoverage(JUnitVersionEnum.JUNIT4);
		
		assertTrue(system.isCovered());
	}

	@Test
	public void testRunReadLogFile() throws CannotReadLogFileException, CoverageUnrealizedException, InstrumentationUnrealizedException{
		String app = PathTo.APP;
		String code = PathTo.APP_CODE.replace(app + PathTo.SEPARATOR, "");
		String lib = PathTo.APP_LIB.replace(app + PathTo.SEPARATOR, "");
		String test = PathTo.APP_TEST.replace(app + PathTo.SEPARATOR, "");
		
		system = new PriorJSystemImpl(app, code, test, lib,"");
		system.runInstrumentation();
		system.runCoverage(JUnitVersionEnum.JUNIT4);
		
		system.readLogFile();
		
		assertTrue(system.isLog());
	}
	
}

