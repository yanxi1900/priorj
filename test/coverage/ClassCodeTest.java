package coverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;

/**
 * Tests for class ClassCode.
 *  
 * @author Samuel T. C. Santos
 * @version 1.0
 * {@link }
 */
public class ClassCodeTest {

	private ClassCode classA;
	private ClassCode classB;
	private ClassCode classC;
	private ClassCode classD;
	
	private String pkg1 = "p1";
	private String pkg2 = "p2";
	
	@Before
	public void setUp(){
		classA = new ClassCode(pkg1 , "A");
		classB = new ClassCode(pkg1 , "B");
		classC = new ClassCode(pkg2 , "C");
		classD = new ClassCode(pkg2 , "D");
	}
	
	@Test
	public void testInitializationClassCode() {
		assertEquals("A", classA.getName());
		assertEquals("B", classB.getName());
		assertEquals("C", classC.getName());
		assertEquals("D", classD.getName());
		
		assertTrue(classA.getNumberMethodCoverage()==0);
		assertTrue(classB.getNumberMethodCoverage()==0);
		assertTrue(classC.getNumberMethodCoverage()==0);
		assertTrue(classD.getNumberMethodCoverage()==0);
		
		assertEquals(pkg1, classA.getPackageName());
		assertEquals(pkg1, classB.getPackageName());
		assertEquals(pkg2, classC.getPackageName());
		assertEquals(pkg2, classD.getPackageName());
	}
	
	@Test
	public void testMethodsCoverage(){
		
		String [] names = {"m1","m2","m3","m4","m5"};
		
		List<Method> methods =  createMethodList(names);
		
		assertTrue(classA.getMethodCoverage().isEmpty());
		
		for (Method method : methods){
			classA.addMethod(method);
		}
		
		List<String> covered = Arrays.asList( "p1.A.m1",
								              "p1.A.m2",
								              "p1.A.m3",
								              "p1.A.m4",
								              "p1.A.m5");
		
		assertArrayEquals(covered.toArray(), classA.getMethodsCoverage().toArray());
		
	}

	@Test
	public void testStatementsCoverage(){
		Method m1, m2, m3, m4, m5;
		
		String [] names = {"m1","m2","m3","m4","m5"};
		
		m1 = new Method(names[0]);
		m2 = new Method(names[1]);
		m3 = new Method(names[2]);
		m4 = new Method(names[3]);
		m5 = new Method(names[4]);
		
		String [] lines1 = {"10", "33", "23"};
		m1.setStatementCoverage(createStatementList(lines1));
		
		String [] lines2 = {"19", "7", "14", "31"};
		m2.setStatementCoverage(createStatementList(lines2));
		
		List<Method> methods = Arrays.asList(m1,m2,m3,m4,m5);
		
		assertTrue(classA.getMethodCoverage().isEmpty());
		
		for (Method method : methods){
			classA.addMethod(method);
		}
		
		List<String> covered = Arrays.asList("p1.A.m1.10",
				            "p1.A.m1.33",
				            "p1.A.m1.23",
				            "p1.A.m2.19",
				            "p1.A.m2.7",
				            "p1.A.m2.14", 
				            "p1.A.m2.31");
		
		assertArrayEquals(covered.toArray(), classA.getStatementCoverage().toArray() );
		assertTrue(classA.getNumberStatemetCoverage() == 7);
		
	}
	
	
	@Test 
	public void testAddMethods(){
		Method m1, m2, m3, m4, m5;
		
		String [] names = {"m1","m2","m3","m4","m5"};
		
		m1 = new Method(names[0]);
		m2 = new Method(names[1]);
		m3 = new Method(names[2]);
		m4 = new Method(names[3]);
		m5 = new Method(names[4]);
		
		List<Method> methods = Arrays.asList(m1,m2,m3,m4,m5);
		
		assertTrue(classA.getMethodCoverage().isEmpty());
		
		for (Method method : methods){
			classA.addMethod(method);
		}
		
		assertArrayEquals(methods.toArray(), classA.getMethodCoverage().toArray());
		assertTrue(classA.getNumberMethodCoverage() == 5);
		assertTrue(classA.getNumberStatemetCoverage() == 0);
	}
	
	@Test
	public void testEquals(){
		assertFalse(classA.equals(classB));	
		assertFalse(classC.equals(classD));
		assertFalse(classA.equals(classC));
		assertFalse(classD.equals(classB));
	}
	
	private List<Statement> createStatementList(String [] lines){
		List<Statement> statements = new LinkedList<Statement>();
		
		for (String line : lines){
			statements.add(new Statement(line));
		}
		
		return statements;
	}
	
	private List<Method> createMethodList(String [] names){
		List<Method> methods = new LinkedList<Method>();
		
		for (String name : names){
			methods.add(new Method(name));
		}
		
		return methods;
	}
	
}
