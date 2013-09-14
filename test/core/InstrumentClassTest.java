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

import util.PathTo;
import util.SubstituiStringArquivo;

/**
 * This class is a test class to class InstrumentClass.
 * 
 * @author Samuel T. C. Santos
 * 		   Igor de Araujo Meira
 * 
 * @version 1.0 2013
 *
 */
public class InstrumentClassTest {
	
	private InstrumentClass ic;
	
	private final String folderInstrumentTests = PathTo.INSTRUMENT_TESTS;
	
	private final String separator = PathTo.SEPARATOR;
	private final String folderBlocks = PathTo.INSTRUMENT_BLOCKS;
	
	@Before
	public void createObject() {
		ic = new InstrumentClass(PathTo.INSTRUMENT_TESTS, PathTo.SEPARATOR  + "CDteca1.java");
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
		assertEquals(PathTo.RESOURCES_FILES + "/InstrumentTests", ic.getPathFile());
		ic.setPathFile("/home/AVL/");
		assertEquals("/home/AVL/", ic.getPathFile());
		
		assertEquals("/CDteca1.java", ic.getNameFile());
		ic.setNameFile("lva");
		assertEquals("lva", ic.getNameFile());
		
		assertEquals("watchPriorJApp", ic.getNameField());
		ic.setNameField("viewPriorJApp");
		assertEquals("viewPriorJApp", ic.getNameField());
	}
	//Normal class
	@Test
	public void testCDteca1() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CDteca1.java");
		
		classPath = classPath + "CDteca1.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 28);
	}
	
	//Classes with "watchPriorJApp = watchPriorJApp;" or "static "static boolean watchPriorJApp;"
	@Test
	public void testCDteca3() throws Exception {		
		/*CDteca3
		 * Contain "static boolean watchPriorJApp;"
		 */		 

		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CDteca3java");
		
		classPath = classPath + "CDteca3.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 0);
	}
	
	//Empty class
	@Test
	public void testCEmpty() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CEmpty.java");
		
		classPath = classPath + "CEmpty.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		//assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 0);
		
	}
	
	//Abstract class
	@Test
	public void testCAbstract() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CAbstract.java");
		
		classPath = classPath + "CAbstract.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 7);
	}
	
	//Interface class
	@Test
	public void testCInterface() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CInterface.java");
		
		classPath = classPath + "CInterface.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertFalse(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 0);
	}
	
	//Class contructor
	@Test
	public void testCConstrutor() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CConstrutor.java");
		
		classPath = classPath + "CConstrutor.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 3);
	}
	
	//Class do-while
	@Test
	public void testCDoWhile() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CDoWhile.java");
		
		classPath = classPath + "CDoWhile.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
		//System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 56);
	}
	
	//Class Switch
	@Test
	public void testCSwitch() throws Exception {
		String classPath = folderInstrumentTests + separator;
		
		ic = new InstrumentClass(classPath, "CSwitch.java");
		
		classPath = classPath + "CSwitch.java";
    
		FileInputStream in = new FileInputStream(classPath);
		CompilationUnit cu = new CompilationUnit();
		cu = JavaParser.parse(in);
		in.close();
		
		ic.instrumentationRun();
		//ic.instrumentarClasse(cu);
		
		assertTrue(cu.toString().contains("watchPriorJApp"));
		System.out.println(ic.getWatchNumber());
		assertTrue(ic.getWatchNumber() == 23);
	}

	/*private void ContaWPAs() {
		contaWPAs = list.size();
	}*/

	/*private void FoundWPA(String fileName, String nameField) {
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
	}*/
}
