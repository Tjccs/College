package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataaccess.TableData.Row;

/**
 * Table Data Gateway for the product's table
 * 
 * Please read the remarks in the CustomerTableGateway class
 *  
 * @author fmartins
 * @version 1.1 (5/10/2015)
 *
 */
public class ProductTableDataGateway extends TableDataGateway {

    // Database schema constants

	/**
     * Table name
     */
    private static final String TABLE_NAME = "product";

    /**
	 * Columns' labels
	 */	
	private static final String ID_COLUMN_NAME = "ID";
	private static final String PRODUCT_CODE_COLUMN_NAME = "PRODCOD";
	private static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";
	private static final String FACE_VALUE_COLUMN_NAME = "FACEVALUE";
	private static final String QTY_COLUMN_NAME = "QTY";
	private static final String DISCOUNT_ELIGIBILITY_COLUMN_NAME = "DISCOUNTELIGIBILITY";
	private static final String UNIT_ID_COLUMN_NAME = "UNIT_ID";

	// SQL statements

	/**
	 * obtain a product by product code 
	 */
	private static final String GET_PRODUCT_BY_PROD_COD_SQL = 
			"select * " +
			"from " + TABLE_NAME + " " + 
			"where " + PRODUCT_CODE_COLUMN_NAME + " = ?";

	/**
	 * The select a product by Id SQL statement
	 */
	private static final String GET_PRODUCT_BY_ID_SQL = 
			"select * " + 
			"from " + TABLE_NAME + " " +
			"where " + ID_COLUMN_NAME + " = ?";

	/**
	 * update product stock
	 */
	private static final String	UPDATE_STOCK_SQL =
			"update " + TABLE_NAME + " " +
			"set " + QTY_COLUMN_NAME + " = ? " +
			"where " + ID_COLUMN_NAME + " = ?";
	
	/**
	 * Creates a product table data gateway for given a data source. 
	 * 
	 * @param dataSource The data source the customer gateway interfaces with
	 */
	ProductTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}

	// 1. interaction with the repository (an SQL database) using JDBC

	// Since this first version of the application does not add new products, 
	// there is no insert method.	

	/**
	 * Gets a product given its product id 
	 * 
	 * @param id The id of the product to search for
	 * @return The in-memory representation of the product
	 * @throws PersistenceException In case there is an error accessing the database.
	 */
	public TableData find (int id) throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(GET_PRODUCT_BY_ID_SQL)) {			
			// set statement arguments
			statement.setInt(1, id);
			// execute SQL
			// see the comment in the findBProdCod
			try (ResultSet rs = statement.executeQuery()) {
				return new TableData().populate(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining a product by its id", e);
		}			
	}

	/**
	 * Gets a product given its codProd 
	 * 
	 * @param prodCod The codProd of the product to search for
	 * @return The in-memory representation of the product
	 * @throws PersistenceException When there is an error interacting with the database
	 */
	public TableData findWithProdCod (int prodCod) throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(GET_PRODUCT_BY_PROD_COD_SQL)) {			
			// set statement arguments
			statement.setInt(1, prodCod);
			// execute SQL
			// Just to close the ResultSet, since same JDBC implementations (like MySql connector
			// and HSqlDB) are broken and do not close the ResultSet upon closing the Statement
			try (ResultSet rs = statement.executeQuery()) {
				return new TableData().populate(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining a product by its code", e);
		}			
	}

	/**
	 * Updates the quantity in stock of a product id
	 * 
	 * @param id The internal code of the product to update its quantity
	 * @param qty The new product stock quantity
	 * @throws PersistenceException When there is an error interacting with the database
	 */
	public void updateStock (int id, double qty) throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(UPDATE_STOCK_SQL)) {			
			// set statement arguments
			statement.setDouble(1, qty);
			statement.setInt(2, id);
			// execute SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Internal error updating product stock", e);
		}			
	}

	
	// 2. Decode a customer row

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readId(Row r) throws PersistenceException {
		return r.getInt(ID_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readProductCode(Row r) throws PersistenceException {
		return r.getInt(PRODUCT_CODE_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public String readDescription(Row r) throws PersistenceException {
		return r.getString(DESCRIPTION_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readFaceValue(Row r) throws PersistenceException {
		return r.getDouble(FACE_VALUE_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readQuantity(Row r) throws PersistenceException {
		return r.getDouble(QTY_COLUMN_NAME);
	}


	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public boolean readDiscountEligibility(Row r) throws PersistenceException {
		return r.getInt(DISCOUNT_ELIGIBILITY_COLUMN_NAME)==1 ;
	}

	/**
	 * Reads from a result set the value of the corresponding column
	 * @param r The row to read from 
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public double readUnitId(Row r) throws PersistenceException {
		return r.getDouble(UNIT_ID_COLUMN_NAME);
	}

}
