package report;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * This class do a replace on method main to generate a new method main.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class ReplaceMethodMain {

	// creates an input stream for the file to be parsed
    private static FileInputStream in;

    private static CompilationUnit cu;
    
	/**
	 * Create a new ReplaceMethodMain.
	 * 
	 * @param classname
	 * 		The path + class name.
	 * 
	 * @throws FileNotFoundException 
	 * 		When the file is not found.
	 * @throws ParseException 
	 */
	public ReplaceMethodMain(String classname) throws FileNotFoundException, ParseException{
		in = new FileInputStream(classname);
		cu = JavaParser.parse(in);
	}
	
	/**
	 * This method find the main method from a Test Suite.
	 * 
	 * @return
	 * 		The main MethodDeclaration.
	 */
	public MethodDeclaration findMainMethod() {
	        List<TypeDeclaration> types = cu.getTypes();
	        for (TypeDeclaration type : types) {
	            List<BodyDeclaration> members = type.getMembers();
	            for (BodyDeclaration member : members) {
	                if (member instanceof MethodDeclaration) {
	                    MethodDeclaration method = (MethodDeclaration) member;
	                    if (method.getName().equals("main")){
	                    	return method;
	                    }
	                }
	            }
	        }
	        return null;
	}
	
	/**
	 * This method change the order into the method main, doing a 
	 * replace to all method by new order in the list passed by argument.
	 * 
	 * @param list
	 * 		A list with the new order to call.
	 */
	public void changeOrderMethodMain(List<String> list){
		MethodDeclaration methodMain = findMainMethod();
		
		BlockStmt block = methodMain.getBody();
				
		Statement firstLine = block.getStmts().get(0);
		
		//clean the body
		methodMain.setBody(new BlockStmt());
		
		BlockStmt newBlock = new BlockStmt();
		
		 ASTHelper.addStmt(newBlock, firstLine);
		for (String methodName : list){
		    NameExpr instance = new NameExpr("main");
	        MethodCallExpr call = new MethodCallExpr(instance, methodName);
	        ASTHelper.addStmt(newBlock, call);
		}
		
		methodMain.setBody(newBlock);
		
		block = methodMain.getBody();
	
	}
	
	/**
	 * This method return the Test suite after modifications.
	 * 
	 * @return
	 * 		new suite changed.
	 */
	public String getCompilationUnit(){
		return cu.toString();
	}
	
}
