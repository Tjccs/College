package dataaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataaccess.TableData.Row;
import datatypes.SaleStatus;

/**
 * Table Data Gateway for the sales's table
 *  
 * @author fmartins
 * @version 1.1 (5/10/2015)
 *
 */
public class SaleTableDataGateway extends TableDataGateway {

    // Database schema constants

    /**
     * Table name
     */
    // TODO
	private static final String TABLE_NAME = "sale";
    /**
	 * Column labels
	 */
	// TODO
	private static final String ID_COLUMN_NAME = "ID";
	private static final String DATE_COLUMN_NAME = "DATE";
	private static final String TOTAL_COLUMN_NAME = "TOTAL";
	private static final String DISCOUNT_TOTAL_COLUMN_NAME = "DISCOUNT_TOTAL";
	private static final String STATUS_COLUMN_NAME = "STATUS";
	private static final String CUSTOMER_ID_COLUMN_NAME = "CUSTOMER_ID";
	/**
	 * A pair of constants to map the status of a sale to a string
	 */
	// TODO
	private static final String OPEN = "1";
	private static final String CLOSED = "2"; 
	
	
	private int saleId;
	
	
	// SQL statements
	
    /**
	 * insert a sale
	 */
    private static final String INSERT_SALE_SQL = 
    		"insert into " + TABLE_NAME + 
			" (" + ID_COLUMN_NAME + ", " + DATE_COLUMN_NAME + ", " + TOTAL_COLUMN_NAME + ", " +
				DISCOUNT_TOTAL_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + ", " + CUSTOMER_ID_COLUMN_NAME + ") " +
		"values (DEFAULT, ?, ?, ?, ?, ?)"; 

	/**
	 * obtain a sale by Id SQL statement
	 */
	private static final String GET_SALE_SQL = 
			"select * " +
					"from " + TABLE_NAME + " " + 
					"where " + ID_COLUMN_NAME + " = ?"; 


	/**
	 * Creates a sale table data gateway for given a data source. 
	 * 
	 * @param dataSource The data source the customer gateway interfaces with
	 */
    SaleTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}


    // 1. interaction with the repository (an SQL database) using JDBC

	/**
	 * Add a new sale to the database
	 * 
	 * @param date The date the sale occurred 
	 * @param saleTotal The total sale amount
	 * @param discountTotal The total discount of the sale
	 * @param saleStatus The sale status 
	 * @param customerId The customer id of the sale
	 * @return The sale id just created
	 * @throws PersistenceException In case an internal database error occurs
	 */
	public int insert (java.util.Date date, double saleTotal, double discountTotal, 
			SaleStatus saleStatus, int customerId) throws PersistenceException {
		// TODO
		try (PreparedStatement statement = dataSource.prepareGetGenKey(INSERT_SALE_SQL)) {
			// set statement arguments
			statement.setDate(1, new java.sql.Date(date.getTime()));
			statement.setDouble(2, saleTotal);
			statement.setDouble(3, discountTotal);
			statement.setString(4, setSaleStatus(saleStatus));
			statement.setInt(5, customerId);
			// execute SQL
			statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				rs.next(); 
				saleId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error adding the customer", e);
		}
		return saleId;
	}

	
	/**
	 * Updates the discount id of the customer
	 * 
	 * @param saleStatus The new sale status
	 */
	public String setSaleStatus(SaleStatus saleStatus) {
		//return saleStatus == SaleStatus.OPEN ? OPEN : 
		//	saleStatus == SaleStatus.CLOSED ? CLOSED : OPEN;
		return (saleStatus == SaleStatus.OPEN) ? OPEN : CLOSED;
	}
	
	
	/**
	 * Gets a sale by its id 
	 * 
	 * @param saleId The sale id to search for
	 * @return The result set with information about the sale
	 * @throws PersistenceException In case there is an error accessing the database.
	 */
	public TableData find (int saleId) throws PersistenceException {
		// TODO
		try (PreparedStatement statement = dataSource.prepare(GET_SALE_SQL)) {			
			// set statement arguments
			statement.setInt(0, saleId);
			// executes SQL
			try (ResultSet rs = statement.executeQuery()) {
				TableData td = new TableData();
				td.populate(rs);
				return td;
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining the sale by id", e);
		}
	}

	
	// 2. Decode a customer row	
	
	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readId(Row r) throws PersistenceException{
		// TODO
		return r.getInt(ID_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public Date readDate(Row r) throws PersistenceException{
		// TODO
		return r.getDate(DATE_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readTotal(Row r) throws PersistenceException{
		// TODO
		return r.getDouble(TOTAL_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readDiscount(Row r) throws PersistenceException{
		// TODO
		return r.getDouble(DISCOUNT_TOTAL_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public SaleStatus readStatus(Row r) throws PersistenceException{
		// TODO
		String ss;
		try {
			
			ss = r.getString(STATUS_COLUMN_NAME);
			/*if (ss.equals(OPEN)) {
			*	return SaleStatus.OPEN;
			*	
			*}else {
			*	return SaleStatus.CLOSED;
			*}
			*/
			return (ss.equals(OPEN)) ? SaleStatus.OPEN : SaleStatus.CLOSED;
		
		}catch (ArrayIndexOutOfBoundsException e) {
			throw new PersistenceException("Internal error converting sale status", e);
		}
	}
	
	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readCustomerId(Row r) throws PersistenceException{
		// TODO
		return r.getInt(CUSTOMER_ID_COLUMN_NAME);
	}

}
