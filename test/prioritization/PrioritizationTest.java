package prioritization;

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
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import main.PriorJ;
import main.PriorJImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import technique.TechniquesEnum;
import coverage.TestCase;


/** 
 * This class verify the prioritization results.
 * 
 * @author Samuel T. C. Santos.
 * @version 1.0
 */
public class PrioritizationTest {

	private Prioritization prioritize;
	private TechniquesEnum technique;
	private List<TestCase> tests;
	private PriorJ priorj;
	
	@Before
	public void setUp(){
		tests = new LinkedList<TestCase>();
		
		technique = TechniquesEnum.Random;
		
		prioritize = new Prioritization(tests, technique.getId(), "", "");
		
		priorj = new PriorJImpl();
	}
	
	@After
	public void setDown(){
		tests = null;
		prioritize = null;
	}
	
	@Test
	public void testInitialization() {
		assertNotNull(technique);
		assertNotNull(prioritize);
	}
	
}
