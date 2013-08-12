package apfd;

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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChartAPFDTest {

	ChartAPFD chartApfd;
	
	@Before
	public void setUp(){
		String [] labels = {"a", "b", "c"};
		int [][] values = {{1,2,3},{3,4,6}, {12, 5,7}};
		chartApfd = new ChartAPFD("chart", labels, values, 3);
	}
	
	@After
	public void tearDown(){
		chartApfd = null;
	}
	
	public void testGetChart(){
		ChartPanel panel = chartApfd.getChart();
	
		assertNotNull(panel);
	}
	
	@Test
	public void testArrayOfArray() {
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		

		assertArrayEquals(new Integer[][] {},list.toArray());
		
		list.add(Arrays.asList(2,6,8));
		list.add(Arrays.asList(5,7,9));
		
		Object []array  = list.toArray();
		
	}

}
