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

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

import util.Settings;
import util.SubstituiStringArquivo;

/**
 * This class is a test class to class InstrumentClass.
 * 
 * @author Samuel T. C. Santos
 * 		   Igor Meira
 * 
 * @version 1.0 2013
 *
 */
public class InstrumentClassTest {
	
	private InstrumentClass ic, ic2, ic3, ic4, ic5, ic6, ic7;
	private List<String> list;
	private int contaWPAs;
	
	private final String separator = Settings.SEPARATOR;
	private final String folderBlocks = Settings.INSTRUMENT_BLOCKS;
	
	@Before
	public void createObject() {
		ic = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CDteca1.java");
		ic2 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CDteca2.java");
		ic3 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CDteca3.java");
		ic4 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CAbstract.java");
		ic5 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  +"CEmpty.java");
		ic6 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CInterface.java");
		ic7 = new InstrumentClass(Settings.INSTRUMENT_TESTS, Settings.SEPARATOR  + "CConstrutor.java");
	}
	
	@Test
	public void testInstrumentClassInitialization(){
		ic = new InstrumentClass("", "");
		
		assertEquals("", ic.getPathFile());
		assertEquals("",ic.getNameFile());
	}
	
	/**
	 * This test try do instrumentation in a empty class.
	 * 
	 * <code>
	 * This is a empty class
	 * public class ClassEmpty {
     *
     * }
     * 
     * this is a instrumented empty class.
	 * public class ClassEmpty {
     *
     *    static boolean watchPriorJApp; 
     * }
     *
	 * </code>
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassWithClassEmpty() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmpty.java");
		
		classPath = classPath + "ClassEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
	}
	
	/**
	 * <p>
	 * This test try do instrumentation in a class empty with a constructor
	 * empty.
	 * 
	 * this is the result: 
	 * </p>
	 * <code>
	 * 
	 * public class ClassEmptyConstructorEmpty {
	 *
     *	 public ClassEmptyConstructorEmpty() {
     *	 }
     * 
	 *   static boolean watchPriorJApp;
	 *	}
	 * </code>
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassClassEmptyWithConstructorEmpty() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorEmpty.java");
		
		classPath = classPath + "ClassEmptyConstructorEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
	}
	
	/**
	 * This is a test to show which class empty with construct and this
	 * inside not is instrumented.
	 * 
	 * <code>
	 * public class ClassEmptyConstructorEmptyWithThis {
     *		public ClassEmptyConstructorWithThis() {
     *   	this();
     *		}
	 *
     * 		static boolean watchPriorJApp;
   	 *	}
   	 *  //result:
   	 *  public class ClassEmptyConstructorEmptyWithThis {
     *
	 *   public ClassEmptyConstructorWithThis() {
	 *         watchPriorJApp = watchPriorJApp;
	 *         this();
	 *       }
	 *
	 *         static boolean watchPriorJApp;
     *  }
     *
	 * </code>
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassClassEmptyConstructorWithThis() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorWithThis.java");
		
		classPath = classPath + "ClassEmptyConstructorWithThis.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
	}
	
	/**
	 * This test try to do an instrumentation in the constructor with super.
	 * <code>
	 * public class ClassEmptyConstructorEmptyWithSuper {
	 *	
	 *	public ClassEmptyConstructorWithThis(){
	 *		super();
	 *	   }
     * }
     * The result:
     * 
     * public class ClassEmptyConstructorEmptyWithSuper {
     *
	 *	    public ClassEmptyConstructorWithThis() {
	 *	        watchPriorJApp = watchPriorJApp;
	 *	        super();
	 *	    }
	 * 	
	 *	    static boolean watchPriorJApp;
	 *	}
	 * </code>
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassClassEmptyConstructorWithSuper() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorWithSuper.java");
		
		classPath = classPath + "ClassEmptyConstructorWithSuper.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
	}
	
	/**
	 * This test instrument a constructor non empty.
	 * <code>
	 * public class ClassEmptyConstructorNonEmpty {
	 *
  	 *	    public ClassEmptyConstructorNonEmpty() {
	 *	        watchPriorJApp = watchPriorJApp;
	 *	        this();
	 *	        watchPriorJApp = watchPriorJApp;
	 *	        String anyName = "blueblue";
	 *	        watchPriorJApp = watchPriorJApp;
	 *	        String anyThing = "any thing";
	 *	        watchPriorJApp = watchPriorJApp;
	 *	        double limit = 1000.0;
	 *	    }
	 *	
 	 *	    static boolean watchPriorJApp;
	 *	}
	 *	
     * </code>
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassClassEmptyConstructorNonEmpty() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorNonEmpty.java");
		
		classPath = classPath + "ClassEmptyConstructorNonEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
		
		assertTrue(ic.getWatchNumber() == 4);
	}
	
	/**
	 * This test try to do instrumentation in a empty class with empty constructor
	 * and empty methods.
	 * see the class code:
	 * <code>
	 * public class ClassEmptyConstructorEmptyMethodEmpty {
	 *	
	 *	    public ClassEmptyConstructorEmptyMethodEmpty() {
	 *	    }
	 *	
	 *	    public void methodA() {
	 *	    }
	 *	
	 *	    public vodi methodB() {
	 *	    }
	 *	
	 *	    public void methodC() {
	 *	    }
	 *	
	 *	    public void methodD() {
	 *	    }
	 *	
	 *	    public void methodE() {
	 *	    }
	 *	
	 *	    static boolean watchPriorJApp;
	 *	}
     *
	 * </code> 
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testInstrumentClassClassEmptyConstructorEmptyMethodEmpty() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorEmptyMethodEmpty.java");
		
		classPath = classPath + "ClassEmptyConstructorEmptyMethodEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
		
		assertTrue(ic.getWatchNumber() == 0);
	}
	
	@Test
	public void testInstrumentClassClassEmptyConstructorEmptyMethodNonEmpty() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassEmptyConstructorEmptyMethodNonEmpty.java");
		
		classPath = classPath + "ClassEmptyConstructorEmptyMethodNonEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
		assertTrue(ic.getWatchNumber() == 6);
		
	}
	/*This test show the instrumentation of class with if-else block.
	 * 
	 * 
	 * 
	 * public class ClassWithBlockIf {

		    public classWithBlockIf() {
		    }
		
		    public void methodBlockWithOneIf() {
		        watchPriorJApp = watchPriorJApp;
		        String a = "a";
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("a")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("a");
		        }
		    }
		
		    public void methodBlockWithManyIf() {
		        watchPriorJApp = watchPriorJApp;
		        String a = "a";
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("a")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("a");
		        }
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("c")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("c");
		        }
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("e")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("e");
		        }
		    }
		
		    public void methodBlockWithIfElse() {
		        watchPriorJApp = watchPriorJApp;
		        String a = "x";
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("a")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("a");
		        } else {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println(a);
		        }
		    }
		
		    public void methodBlockWithIfElseIf() {
		        watchPriorJApp = watchPriorJApp;
		        String a = "a";
		        watchPriorJApp = watchPriorJApp;
		        if (a.equals("a")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("a");
		        } else if (a.equals("c")) {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println(a);
		        } else {
		            watchPriorJApp = watchPriorJApp;
		            System.out.println("k");
		        }
		    }
		
		    static boolean watchPriorJApp;
		}

	 * 
	 */
	@Test
	public void testInstrumentClassWithBlockIfIfElseIf() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassWithBlockIF.java");
		
		classPath = classPath + "ClassWithBlockIF.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
		assertTrue(ic.getWatchNumber() == 19);
		
	}
	
	/*
	 * This test show the instrumentation in the class with for blocks.
	 *
	 *public class ClassWithBlockFor {

		    public classWithBlockFor() {
		    }
		
		    public void methodBlockFor() {
		        watchPriorJApp = watchPriorJApp;
		        for (int i = 0; i < 10; i++) {
		            watchPriorJApp = watchPriorJApp;
		            int c = i;
		            watchPriorJApp = watchPriorJApp;
		            int d = c - 1;
		        }
		    }
		
		    public void methodBlockEmptyFor() {
		        watchPriorJApp = watchPriorJApp;
		        for (int i = 0; i < 5; i++) {
		        }
		    }
		
		    public void methodBlockInfiniteForEmpty() {
		        watchPriorJApp = watchPriorJApp;
		        for (; ; ) {
		        }
		    }
		
		    public void methodBlockInfiniteForNonEmpty() {
		        watchPriorJApp = watchPriorJApp;
		        for (; ; ) {
		            watchPriorJApp = watchPriorJApp;
		            int i = 0;
		        }
		    }
		
		    public void methodBlockWithManyFor() {
		        watchPriorJApp = watchPriorJApp;
		        for (int i = 0; i < 10; i++) {
		            watchPriorJApp = watchPriorJApp;
		            int c = i;
		            watchPriorJApp = watchPriorJApp;
		            int d = c - 1;
		        }
		        watchPriorJApp = watchPriorJApp;
		        for (int i = 0; i < 10; i++) {
		            watchPriorJApp = watchPriorJApp;
		            int c = i;
		            watchPriorJApp = watchPriorJApp;
		            int d = c - 1;
		        }
		    }
		
		    public void methodBlockWithNestedFor() {
		        watchPriorJApp = watchPriorJApp;
		        for (int i = 0; i < 10; i++) {
		            watchPriorJApp = watchPriorJApp;
		            for (int j = 0; j < 10; j++) {
		                watchPriorJApp = watchPriorJApp;
		                for (int k = 0; k < 10; k++) {
		                    watchPriorJApp = watchPriorJApp;
		                    int c = i + j - k;
		                }
		            }
		        }
		    }
		
		    public void methodBlockWithNestedForEmpty() {
		        watchPriorJApp = watchPriorJApp;
		        for (; ; ) {
		            watchPriorJApp = watchPriorJApp;
		            for (; ; ) {
		                watchPriorJApp = watchPriorJApp;
		                for (; ; ) {
		                }
		            }
		        }
		    }
		
		    public void methodBlockWithForEach() {
		        watchPriorJApp = watchPriorJApp;
		        List<String> list = new ArrayList<String>();
		        watchPriorJApp = watchPriorJApp;
		        for (String s : list) {
		        }
		        watchPriorJApp = watchPriorJApp;
		        for (Object s : list) {
		            watchPriorJApp = watchPriorJApp;
		            String a = (String) s;
		        }
		        watchPriorJApp = watchPriorJApp;
		        for (String s : list) {
		            watchPriorJApp = watchPriorJApp;
		            for (String x : list) {
		                watchPriorJApp = watchPriorJApp;
		                for (String v : list) {
		                    watchPriorJApp = watchPriorJApp;
		                    System.out.println(s + x + v);
		                }
		            }
		        }
		    }
		
		    static boolean watchPriorJApp;
		}

     *
	 */
	
	@Test
	public void testInstrumentClassWithBlockFor() throws ParseException, IOException{
		String classPath = folderBlocks + separator;
		
		ic = new InstrumentClass(classPath, "ClassWithBlockFor.java");
		
		classPath = classPath + "ClassWithBlockFor.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		System.out.println(cu);
		assertTrue(cu.toString().contains("watchPriorJApp"));
		assertTrue(ic.getWatchNumber() == 28);
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
