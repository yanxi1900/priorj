package sales;

/**
 * <p>
 * 		This is an abstract class which representing a entity product.
 *  	A product has price, product name e product description.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public abstract class Product {
	
	protected String productName;
	protected float price;
	protected String description;
 
	/**
	 * <p>
	 *   This is an abstract method which to do a discount in the product price.
	 * 	
	 * 	 An discount is a percentages ,1% or  5%, and so on.
	 * </p>
	 * @param value
	 */
	public abstract void discount(float value);

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", price=" + price + ", description=" + description + "]";
	}
	
	
}
