package core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

import util.Settings;
import util.SubstituiStringArquivo;

public class InstrumentClassTest {
	
	private InstrumentClass ic, ic2, ic3, ic4, ic5, ic6, ic7;
	private List<String> list;
	private int contaWPAs;
	
	@Before
	public void createObject() {
		ic = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CDteca1.java");
		ic2 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CDteca2.java");
		ic3 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CDteca3.java");
		ic4 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CAbstract.java");
		ic5 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CEmpty.java");
		ic6 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CInterface.java");
		ic7 = new InstrumentClass(Settings.RESOURCES_FILES + "/InstrumentTests", "/CConstrutor.java");
		
	}
	
	@Test
	public void testGetsSets() {
		assertEquals(Settings.RESOURCES_FILES + "/InstrumentTests", ic.getPathFile());
		ic.setPathFile("/home/AVL/");
		assertEquals("/home/AVL/", ic.getPathFile());
		
		assertEquals("/CDteca1.java", ic.getNameFile());
		ic.setNameFile("lva");
		assertEquals("lva", ic.getNameFile());
		
		assertEquals("watchPriorJApp", ic.getNameField());
		ic.setNameField("viewPriorJApp");
		//assertEquals("watchPriorJApp", ic.getNameField());
		assertEquals("viewPriorJApp", ic.getNameField());
	}
	//Normal class
	@Test
	public void testCDteca1() throws Exception {
		ic.instrumentationRun();
		FoundWPA(ic.getPathFile() + ic.getNameFile(), ic.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 26);
	}
	
	//Classes with "watchPriorJApp = watchPriorJApp;" or "static "static boolean watchPriorJApp;"
	@Test
	public void testCDteca2_3() throws Exception {		
		/*CDteca3
		 * "static "static boolean watchPriorJApp;"
		 */
		ic3.instrumentationRun();
		FoundWPA(ic3.getPathFile() + ic3.getNameFile(), ic3.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 1);
		
		/*CDteca2
		 * "watchPriorJApp = watchPriorJApp;"
		 */
		ic2.instrumentationRun();
		FoundWPA(ic2.getPathFile() + ic2.getNameFile(), ic2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 26);
	}
	
	//Empty class
	@Test
	public void testCEmpty() throws Exception {
		try {

			ic5.instrumentationRun();
			fail();
			
			FoundWPA(ic5.getPathFile() + ic5.getNameFile(), ic5.getNameField());
			ContaWPAs();
			assertTrue(contaWPAs == 0);
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	
	//Abstract class
	@Test
	public void testCAbstract() throws Exception {
		ic4.instrumentationRun();
		FoundWPA(ic4.getPathFile() + ic4.getNameFile(), ic4.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 7);
	}
	
	//Interface class
	@Test
	public void testCInterface() throws Exception {
		ic6.instrumentationRun();
		FoundWPA(ic6.getPathFile() + ic6.getNameFile(), ic6.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
	}
	
	//Class contructor
	@Test
	public void testCConstrutor() throws Exception {
		ic7.instrumentationRun();
		FoundWPA(ic7.getPathFile() + ic7.getNameFile(), ic7.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 4);
	}

	private void ContaWPAs() {
		contaWPAs = list.size();
	}

	private void FoundWPA(String fileName, String nameField) {
		String nameFile = fileName;
		list = new LinkedList<String>();	        
	    try {
	    	BufferedReader in = new BufferedReader(new FileReader(nameFile));
	        String str;
	        while (in.ready()) {
	        	str = in.readLine();
	            if (str.contains(nameField)){
	            	list.add(nameField);
	                }
	            }
	        in.close();
	    } catch (IOException e) {
	    System.out.println("Read file and return list error: " + e.getMessage());
	    }	
	}
}
