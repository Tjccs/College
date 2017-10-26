package dataaccess;


import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.DiscountType;
import business.SaleStatus;

/**
 * An in-memory representation of a customer table record. 
 *	
 * Notes:
 * 1. See the notes for the CustomerRowGateway class
 * 
 * 2. Java makes available two Date classes (in fact, more in Java 8, 
 * but we will address it later (with JPA)): one in the package 
 * java.util, which is the one we normally use, and another in
 * java.sql, which is a subclass of java.util.date and that 
 * transforms the milliseconds representation according to the
 * "Date type of databases". For more information refer to 
 * http://download.oracle.com/javase/6/docs/api/java/sql/Date.html.
 * 
 * 3. When creating a new sale, we only pass the date and customer id 
 * parameters to the constructor. Moreover, attribute open is always 
 * set to 'O'. The remaining attributes are either set automatically (id) 
 * or when closing the sale (totalSale and totalDiscount). Also, the open 
 * attribute is set to 'C' upon payment. 
 * 
 * @author fmartins
 * @Version 1.2 (13/02/2015)
 *
 */
public class SaleRowDataGateway {

	// Sale attributes 
	
	private int id;
	private int customerId;
	private java.sql.Date date;
	private double totalDiscount;
	private double totalSale;
	SaleStatus saleStatus;
	
	/**
	 * The select a sale by Id SQL statement
	 */
	private static final String GET_SALE_SQL = 
			"select id, date, total, discount_total, status, customer_id from sale where id = ?"; // TODO: program me!
	
	/**
	 * The insert sale SQL statement
	 */
	private static final String INSERT_SALE_SQL = 
			"insert into sale (date, total, discount_total, status, customer_id) " +
					"values (DEFAULT, ?, ?, ?, ?, ?)"; 
	// Constants for conversion of status

	// 1. constructor 

	/**
	 * Creates a new sale given the customer Id and the date it occurs.
	 * 
	 * @param customerId The customer Id the sale belongs to
	 * @param date The date the sale took place
	 */
	public SaleRowDataGateway(int customerId, Date date) {
		// TODO Auto-generated method stub
		this.customerId = customerId;
		this.date = new java.sql.Date(date.getTime());
		this.totalDiscount = 0;
		this.totalSale = 0;
		this.saleStatus = SaleStatus.OPEN;
	}

	// 2. getters and setters

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public SaleStatus getStatus() {
		// TODO Auto-generated method stub
		return toSaleStatus(saleStatus);
	}

	public int getCustomerId() {
		// TODO Auto-generated method stub
		return customerId;
	}

	public double getDiscount() {
		// TODO Auto-generated method stub
		return totalDiscount;
	}
	
	/**
	 * Updates the discount id of the customer
	 * 
	 * @param discountType The new discount type
	 */
	public void setSaleStatus(SaleStatus saleStatus) {
		this.saleStatus= saleStatus == SaleStatus.SALE_AMOUNT ? SALE_AMOUNT : 
			discountType == DiscountType.ELIGIBLE_PRODUCTS ? ELIGIBLE_PRODUCTS : NO_DISCOUNT;
	}

	/**
	 * Converts an integer into a discount type. Used for decoding the persisted value.
	 *  
	 * @param discountId The integer to convert into a discount type. Invalid discount types
	 * are converted to DiscountType.NO_DISCOUNT.
	 * @return The discount type corresponding to an integer.
	 */
	private static SaleStatus toSaleStatus(int saleStatus) {
		return saleStatus == ELIGIBLE_PRODUCTS ? DiscountType.ELIGIBLE_PRODUCTS : 
			discountId == SALE_AMOUNT ? DiscountType.SALE_AMOUNT : DiscountType.NO_DISCOUNT;
	}
	
	// 3. interaction with the repository (a memory map in this simple example)

	/**
	 * Stores the information in the repository
	 */
	public void insert () throws PersistenceException {		
		// TODO: program me!
		try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_SALE_SQL)) {
			// set statement arguments
			statement.setDate(1, date);
			statement.setDouble(2, totalSale);
			statement.setDouble(3, totalDiscount);
			statement.setInt(4, saleStatus);
			statement.setInt(5, customerId);
			// executes SQL
			statement.executeUpdate();
			// Gets customer Id generated automatically by the database engine
			try (ResultSet rs = statement.getGeneratedKeys()) {
				rs.next(); 
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenceException ("Internal error!", e);
		}
	}
	
	
	
	
	/**
	 * Gets a sale by its id 
	 * 
	 * @param id The sale id to search for
	 * @return The new object that represents an in-memory sale
	 * @throws PersistenceException In case there is an error accessing the database.
	 */
	public static SaleRowDataGateway find (int id) throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_SALE_SQL)) {			
			// set statement arguments
			statement.setInt(1, id);
			// executes SQL
			try (ResultSet rs = statement.executeQuery()) {
				// creates a new customer from the data retrieved from the database
				return loadSale(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error getting a customer by its id", e);
		}
	}

	
	/**
	 * Creates a sale from a result set retrieved from the database.
	 * 
	 * @param rs The result set with the information to create the sale.
	 * @return A new sale loaded from the database.
	 * @throws RecordNotFoundException In case the result set is empty.
	 */
	private static SaleRowDataGateway loadSale(ResultSet rs) throws RecordNotFoundException {
		try {
			rs.next();
			SaleRowDataGateway newSale = new SaleRowDataGateway(rs.getInt("id"), rs.getDate("date"));
			newSale.id = rs.getInt("id");
			return newSale;
		} catch (SQLException e) {
			throw new RecordNotFoundException ("Customer does not exist", e);
		}
	}
}
