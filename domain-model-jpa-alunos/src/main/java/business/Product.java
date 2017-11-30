package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * A product 
 * 
 * @author fmartins
 * @version 1.1 (17/04/2015)
 *
 */
@Entity
@NamedQuery(name=Product.FIND_BY_PRODCOD, query="SELECT p FROM Product p WHERE p.prodCod = :" + 
		Product.NUMBER_PRODCOD)
public class Product {
	

	
		// Named query name constants
		public static final String FIND_BY_PRODCOD = "Prodcut.findByProdCod";
		public static final String NUMBER_PRODCOD = "ProdCode";

	// Customer attributes 
	
		
	@Id @GeneratedValue
	private int id;
	/**
	 * The product code. This code is the one present in the product that customers have access to.
	 */	
	@Column(nullable = false, unique = true) private int prodCod;
	
	/**
	 * Product's description 
	 */
	@SuppressWarnings("unused") private String description;
	
	/**
	 * Product's face value
	 */
	@Column(nullable = false) private double faceValue;

	/**
	 * Product's stock quantity
	 */
	@Column(nullable = false) private double qty;

	/**
	 * If the product is eligible for a discount
	 */
	@Column(nullable = false) private boolean discountEligibility;

	/**
	 * Product's units
	 */
	@SuppressWarnings("unused") private Unit unit;
	
	/**
	 * Constructor needed by JPA 
	 */
	Product() {
	}
	
	/**
	 * Creates a new product given its code, description, face value, 
	 * stock quantity, if it is eligible for discount, and its units.
	 * 
	 * @param prodCod The product code
	 * @param description The product description
	 * @param faceValue The value by which the product should be sold
	 * @param qty The number of units available in stock
	 * @param discountEligibility If the product is eligible for discount
	 * @param unitId The units of the product quantity.	
	 */
	public Product(int prodCod, String description,
			double faceValue, double qty, boolean discountEligibility,
			Unit unit) {
		this.prodCod = prodCod;
		this.description = description;
		this.faceValue = faceValue;
		this.qty = qty;
		this.discountEligibility = discountEligibility;
		this.unit = unit;
	}
	

	// 2. getters and setters
	
	/**
	 * Comment: there is a business rule to not allow product code changes.
	 * That is why there is no method for updating the product code.
	 * 
	 * @return The code of the product.
	 */
	public int getProdCod() {
		return prodCod;
	}

	/**
	 * @return The product's face value
	 */
	public double getFaceValue() {
		return faceValue;
	}

	/**
	 * @return The product's quantity
	 */
	public double getQty() {
		return qty;
	}

	/**
	 * Updates the product's stock quantity
	 * 
	 * @param qty The new stock quantity
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}

	/**
	 * @return whether the product is eligible for discount
	 */
	public boolean isEligibleForDiscount() {
		return discountEligibility;
	}	
	
	
}
