package project;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import exception.EmptyPriorJProjectNameException;

/**
 * This class is a test for class PriorJProject.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class PriorJProjectTest {

	private PriorJProject project;
	private Calendar today;
	private JUnitVersionEnum version;
	
	@Before
	public void setUp(){
		project = new PriorJProject();
		today = Calendar.getInstance();
		version = JUnitVersionEnum.JUNIT3;
	}
	
	@Test(expected = EmptyPriorJProjectNameException.class)
	public void testCreateProjectWithEmptyName() throws EmptyPriorJProjectNameException {
		project = new PriorJProject("", today, version);
	}

}
