package core;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

public class InstrumentAppTest {

	InstrumentApp instApp;
	
	@Before
	public void setUp() throws Exception {
		instApp = new InstrumentApp("", "");
	}

	@After
	public void tearDown() throws Exception {
		instApp = null;
	}

	@Test
	public void testExcludePathTest(){
		File f = new File(PathTo.APP_AVL_TEST);
		
		System.out.println(f.getPath());
		
	}
	
	@Test
	public void testInstrumentAppWithFilter() {
		
	}

}
