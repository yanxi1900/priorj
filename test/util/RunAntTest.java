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
		String app = PathTo.APP;
		String code = PathTo.APP_CODE.replace(app + PathTo.SEPARATOR, "");
		String lib = PathTo.APP_LIB.replace(app + PathTo.SEPARATOR, "");
		String test = PathTo.APP_TEST.replace(app + PathTo.SEPARATOR, "");
		
		runAnt = new RunAnt(app,code, test,lib);
		
		runAnt.run();
	}

	
}
