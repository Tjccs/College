package business;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * A sale
 *	
 * @author fmartins
 * @version 1.1 (17/04/2015)
 * 
 */

@Entity

@NamedQuery(name=Sale.FIND_BY_ID, query="SELECT s FROM Sale s WHERE s.id = :" + 
		Sale.NUMBER_ID_NUMBER)


	

public class Sale {
	// Named query name constants
		public static final String FIND_BY_ID = "Sale.findById";
		public static final String NUMBER_ID_NUMBER = "idNumber";
		
	@Id @GeneratedValue private int id;
	
	/**
	 * The date the sale was made 
	 */
	@Column(nullable = false) private Date date;

	/**
	 * Whether the sale is open or closed. 
	 */
	private SaleStatus status;
	
	@OneToOne @JoinColumn(nullable = false) private Customer customer;
	
	/**
	 * The products of the sale
	 */
	@JoinColumn(nullable = false) private List<SaleProduct> saleProducts;
		
	
	// 1. constructor
	Sale() {
	}
	
	/**
	 * Creates a new sale given the date it occurred and the customer that
	 * made the purchase.
	 * 
	 * @param date The date that the sale occurred
	 * @param customer The customer that made the purchase
	 */
	public Sale(Date date, Customer customer) {
		this.date = date;
		this.customer = customer;
		this.status = SaleStatus.OPEN;
		this.saleProducts = new LinkedList<SaleProduct>();
	}

	
	// 2. getters and setters

	/**
	 * @return The sale's total 
	 */
	public double total() {
		double total = 0;
		for (SaleProduct sp : saleProducts)
			total += sp.getSubTotal();
		return total;
	}

	/**
	 * @return The sale's amount eligible for discount
	 */
	public double eligibleDiscountTotal () {
		double total = 0;
		for (SaleProduct sp : saleProducts)
			total += sp.getEligibleSubtotal();
		return total;
	}
	
	/**
	 * @return Computes the sale's discount amount based on the discount type of the customer.
	 */
	public double discount () {
		Discount discount = customer.getDiscountType();
		return discount.computeDiscount(this);
	}

	/**
	 * @return Whether the sale is open
	 */
	public boolean isOpen() {
		return status == SaleStatus.OPEN;
	}

	/**
	 * Adds a product to the sale
	 * 
	 * @param product The product to sale
	 * @param qty The amount of the product being sold
	 * @throws ApplicationException 
	 */
	public void addProductToSale(Product product, double qty) 
			throws ApplicationException {
		if (!isOpen())
			throw new ApplicationException("Cannot add products to a closed sale.");

		// if there is enough stock
		if (product.getQty() >= qty) {
			// adds product to sale
			product.setQty(product.getQty() - qty);
			saleProducts.add(new SaleProduct(product, qty));
		} else
			throw new ApplicationException("Product " + product.getProdCod() + " has stock ("  + 
							product.getQty() + ") which is insuficient for the current sale");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SaleProduct sp : saleProducts)
			sb.append(" ["+sp.getProduct().getProdCod() +":"+sp.getQty()+"]");
		return sb.toString();
	}
}
