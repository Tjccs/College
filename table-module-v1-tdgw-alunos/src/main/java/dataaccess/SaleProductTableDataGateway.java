package dataaccess;

import dataaccess.TableData.Row;

/**
 * Table Data Gateway for the salesproduct's table
 * 
 *   
 * @author fmartins
 * @version 1.1 (5/10/2015)
 *
 */
public class SaleProductTableDataGateway extends TableDataGateway {

    // Database schema constants

    /**
     * Table name
     */
	// TODO

	/**
	 * Column labels
	 */
	// TODO

	
	// SQL statements

	/**
	 * insert a product in a sale
	 */
   private static final String INSERT_PRODUCT_SALE_SQL = ""; // TODO
	
	/**
	 * obtain the products of a sale by sale Id 
	 */
	private static final String GET_SALE_PRODUCTS_SQL = ""; // TODO
	

	/**
	 * Creates a sale product table data gateway for given a data source. 
	 * 
	 * @param dataSource The data source the customer gateway interfaces with
	 */
	SaleProductTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}

    // 1. interaction with the repository (an SQL database) using JDBC

	/**
	 * Inserts the record in the products sale 
	 * 
	 * @param saleId The id of the sale to add the product to
	 * @param productId The id of the product to add to the sale
	 * @param qty The amount of the product to be sold
	 * @throws PersistenceException When an internal error adding a product to a sale occurs
	 */
	public void insert (int saleId, int productId, double qty) throws PersistenceException {
		// TODO
	}
	
	
	/**
	 * Gets the products of a sale by its sale id 
	 * 
	 * @param saleId The sale id to get the products of
	 * @return The set of products that compose the sale
	 * @throws PersistenceException When there is an error obtaining the
	 *         information from the database.
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
	public double readQuantity(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readSaleId(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readProductId(Row r) throws PersistenceException{
		// TODO
		return 0;
	}

}
