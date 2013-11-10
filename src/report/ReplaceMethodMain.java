package report;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
}
