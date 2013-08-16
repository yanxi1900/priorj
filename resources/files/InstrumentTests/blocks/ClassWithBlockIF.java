public class ClassWithBlockIf {
	
	public classWithBlockIF(){
		
	}
	
	public void methodBlockWithOneIf(){
		String a = "a";
		
		if ( a.equals("a") ){
			System.out.println("a");
		}
	}
	
	public void methodBlockWithManyIf(){
		String a = "a";
		
		if ( a.equals("a") ){
			System.out.println("a");
		}
		
		if ( a.equals("c") ){
			System.out.println("c");
		}
		
		if ( a.equals("e") ){
			System.out.println("e");
		}
	}
	
	public void methodBlockWithIfElse(){
		String a = "x";
		
		if ( a.equals("a") ){
			System.out.println("a");
		}
		else{
			System.out.println(a);
		}
	}
	
	public void methodBlockWithIfElseIf(){
		String a = "a";
		
		if ( a.equals("a") ){
			System.out.println("a");
		}
		else if(a.equals("c")){
			System.out.println(a);
		}
		else {
			System.out.println("k");
		}
	}
	
	
	
}