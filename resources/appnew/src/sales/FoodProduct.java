package sales;

/**
 * <p>
 * 		This class represent a food product.
 * 
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class FoodProduct extends Product {

	/**
	 * Create a new product with name, price and discription.
	 * 
	 * @param name
	 * @param price
	 * @param description
	 */
	public FoodProduct(String name, float price, String description) {
		// TODO Auto-generated constructor stub
		this.productName = name;
		this.price = price;
		this.description = description;
	}

	/**
	 * Default construct.
	 */
	public FoodProduct(){	
	}
	
	@Override
	public void discount(float value) {
		this.price = price * value;
	}

}
