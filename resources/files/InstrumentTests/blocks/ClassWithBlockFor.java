public class ClassWithBlockFor {
	
	public classWithBlockFor(){
		
	}
	
	public void methodBlockFor(){
		for (int i=0; i< 10; i++){
			int c = i;
			int d = c-1;
		}
	}
	
	public void methodBlockEmptyFor(){
		for (int i = 0; i<5; i++){
			
		}
	}
	
	public void methodBlockInfiniteForEmpty(){
		for (;;){
			
		}
	}
	
	public void methodBlockInfiniteForNonEmpty(){
		for (;;){
			int i = 0;
		}
	}
	
	public void methodBlockWithManyFor(){
		for (int i=0; i< 10; i++){
			int c = i;
			int d = c-1;
		}
		
		for (int i=0; i< 10; i++){
			int c = i;
			int d = c-1;
		}
	}
	
	public void methodBlockWithNestedFor(){
		for (int i=0; i< 10; i++){
			for (int j=0; j< 10; j++){
				for (int k=0; k< 10; k++){
					int c = i + j - k;
				}
			}
		}
	}
	
	public void methodBlockWithNestedForEmpty(){
		for(;;){
			for(;;){
				for(;;){
					
				}
			}
		}
	}
	
	public void methodBlockWithForEach(){
		List<String> list = new ArrayList<String> ();
		
		for (String s : list){
			
		}
		
		for (Object s : list){
			String a = (String) s;
		}
		
		for (String s: list){
			for (String x: list){
				for (String v: list){
					System.out.println(s+x+v);
				}
			}
		}
		
	}
	
	
}