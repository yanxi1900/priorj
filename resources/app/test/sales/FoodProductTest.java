package sales;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FoodProductTest {

	Product product;


	@Before
	public void setUp() throws Exception {
		product = new FoodProduct();
	}

	@After
	public void tearDown() throws Exception {
		product = null;
	}

	@Test
	public void testCreate() {
		product = new FoodProduct("milk", new Float(2.4), "this is a milk");
		
		assertEquals("milk", product.getProductName());
		assertTrue( product.getPrice() == new Float(2.4));
		assertEquals("this is a milk", product.getDescription());
	}

}
