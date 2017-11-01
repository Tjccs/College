package dataaccess;

import java.sql.Date;

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

    /**
	 * Column labels
	 */
	// TODO
	
	/**
	 * A pair of constants to map the status of a sale to a string
	 */
	// TODO
	
	
	// SQL statements
	
    /**
	 * insert a sale
	 */
    private static final String INSERT_SALE_SQL = ""; // TODO


	/**
	 * obtain a sale by Id SQL statement
	 */
	private static final String GET_SALE_SQL = ""; // TODO


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
		return 0;
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
		return null;
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
		return 0;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public Date readDate(Row r) throws PersistenceException{
		// TODO
		return null;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readTotal(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readDiscount(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public SaleStatus readStatus(Row r) throws PersistenceException{
		// TODO
		return null;
	}
	
	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readCustomerId(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

}
