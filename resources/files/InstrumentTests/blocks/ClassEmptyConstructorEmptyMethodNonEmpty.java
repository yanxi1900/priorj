public class ClassEmptyConstructorEmptyMethodEmpty {
	
	public ClassEmptyConstructorEmptyMethodEmpty(){
	
	}
	
	public void methodA(){
		String a = "a";
	}
	
	public void methodB(){
		String b = "squl";
	}
	
	public void methodC(){
		String c = "c";
	}
	
	public void methodD(){
		String d = methodA() + methodB();
		String e = methodC();
	}
	
	public void methodE(){
		String f = "d";
	}
	
}