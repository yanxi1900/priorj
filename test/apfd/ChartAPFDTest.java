package apfd;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ChartAPFDTest {

	@Test
	public void testArrayOfArray() {
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		

		assertArrayEquals(new Integer[][] {},list.toArray());
		
		list.add(Arrays.asList(2,6,8));
		list.add(Arrays.asList(5,7,9));
		
		Object []array  = list.toArray();
		
	}

}
