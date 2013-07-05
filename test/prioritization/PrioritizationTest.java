package prioritization;

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
