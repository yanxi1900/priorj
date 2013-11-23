package report;

import static org.junit.Assert.*;
import japa.parser.ASTHelper;
import japa.parser.ParseException;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

/**
 * This class is an test to class under test <code> ReplaceMethodMain</code>.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class ReplaceMethodMainTest {

	private ReplaceMethodMain replace;
	
	private String pathfile = "";
	
	
	@Before 
	public void setUp() throws FileNotFoundException, ParseException{
		pathfile = PathTo.RESOURCES_FILES + PathTo.SEPARATOR + "Main.java";
		
		replace = new ReplaceMethodMain(pathfile);
	}
	
	@After
	public void tearDown(){
		pathfile = "";
		replace = null;
	}
	
	
	@Test
	public void testLocateMainMethod() {
		
		MethodDeclaration methodDec = replace.findMainMethod();
		
		assertEquals("name should be main!", "main",methodDec.getName());
	}

	@Test
	public void testMethodDeclarationExpressions(){
		MethodDeclaration methodDec = replace.findMainMethod();
		
		BlockStmt block = methodDec.getBody();
		
		BlockStmt newBlock = new BlockStmt();
				
	    NameExpr instance = new NameExpr("main");
        MethodCallExpr call = new MethodCallExpr(instance, "d");
        ASTHelper.addStmt(block, call);
		
		for (Statement stmt : block.getStmts()){
			System.out.println(stmt.toString());
			
		}
	}
	
	@Test
	public void tryChangeOrder(){
		
		String initialOrder = replace.getCompilationUnit();
		
		List<String> list = Arrays.asList("a", "x", "d","k");
				
		replace.changeOrderMethodMain(list);
		
		String changedOrder = replace.getCompilationUnit();
		
		assertFalse(initialOrder.equals(changedOrder));
	}
	
	
	
}
