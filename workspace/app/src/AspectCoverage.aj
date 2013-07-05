package aspectfiles;

import org.junit.Test;
import edu.ufcg.splab.priorj.dao.Repository;

public aspect AspectCoverage {

	private Repository repository;
		
	pointcut callTestCase() : execution(@Test * *(..))  && (within(*calctest.*));
		
    pointcut callMethod() : execution(* *(..)) && !execution(* calctest*..*.*(..)) 
	&& !execution(* junit..*..*.*(..)) && !execution(* java..*..*.*(..)) && !within(AspectCoverage);

	pointcut callWatchPrior() : get(* *.watchPriorJApp);
	
	before() : callTestCase(){
		repository = new Repository();
        
        String pkgName = thisJoinPoint.getSignature().getDeclaringType().getPackage().getName();
        
        String suiteName = thisJoinPoint.getTarget().getClass().getSimpleName();
        String tcName = thisJoinPoint.getSignature().getName();
        repository.addTestCase(pkgName, suiteName+".java", tcName);
        
	}
	
	after() : callTestCase(){
		String tcName = thisJoinPoint.getSignature().getName();
		repository.commit();
	}
	
	before(): callMethod(){
	
		Class pck = thisJoinPoint.getSignature().getDeclaringType();
		String pckName = "";
		if (pck.getPackage() != null) {
			pckName = thisJoinPoint.getSignature().getDeclaringType().getPackage().getName();
		}
		
		String classeName = thisJoinPoint.getSignature().getDeclaringType().getName();
		
		classeName = classeName.replace(pckName+".", "");	
		
		//get the method name in the form "methodName(param, param , ... , param)"
		String name = thisJoinPoint.getSignature().toString();
		int index =  name.lastIndexOf(".")+1;
		name = name.substring(index, name.length());
		
		if(repository != null)
			repository.addMethod(pckName, classeName, name);
	}
	
	before(): callWatchPrior(){
		int lineNumber = thisJoinPointStaticPart.getSourceLocation().getLine() + 1;
		String className = thisJoinPoint.getSourceLocation().getFileName();
		if(repository != null)
			repository.addStatement(lineNumber + "");
	}
	
	
}
