package calc;

public class Calculator implements ICalc {

	public void Calculator(){
		
	}
	
	public double add(double x, double y){
		return x+y;
	}
	
	public double sub(double x, double y){
		return x-y;
	}
	
	public double mul(double x, double y){
		return x*y;
	}
	
	public double division(double x, double y){
		if (y==0)
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		
		return x / y;
	}
	
	public int mod(int x, int y){
		return x % y;
	}
	
}
