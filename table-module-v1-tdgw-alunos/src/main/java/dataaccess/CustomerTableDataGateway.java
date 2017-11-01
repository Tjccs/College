package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataaccess.TableData.Row;
import datatypes.DiscountType;

/**
 * Table Data Gateway for the customer's table
 *
 * Remarks:
 * 1. The SQL string should not be constants in this class. We illustrated
 * such a scenario in version 2 of the RowDataGateway persistence package 
 * (used in conjunction with the Transaction script pattern).
 * 
 * 2. In the TableDataGateway pattern, data is return as a result set, instead
 * a list of objects, one per table row. I'm using the ResultSet class from the
 * JDBC API. 
 * 
 * 3. We are using public constants to refer to table field names. Since TableDataGateway
 * objects return result sets, the business layer must access these result sets in order to 
 * extract information. There are two ways of accessing fields via in result set: by its number
 * in the result set row or by its name. I prefer to use names instead of numbers, since
 * it is more descriptive that a number, and, after changing the database schema, 
 * it minimises the changes impact (only field that changed name need to be altered).
 * . 
 * These constants allow us to eliminate the coupling of field names in the business layer.    
 * 
 * @author fmartins
 * @author antonialopes
 * @version 1.2 (5/10/2014)
 *
 */
public class CustomerTableDataGateway extends TableDataGateway {

    // Database schema constants

	/**
	 * Table name
	 */
	private static final String TABLE_NAME = "customer";

	/**
     * Columns' labels
	 */	
	private static final String ID_COLUMN_NAME = "ID";
	private static final String VAT_NUMBER_COLUMN_NAME = "VATNUMBER";
	private static final String DESIGNATION_COLUMN_NAME = "DESIGNATION";
	private static final String PHONE_NUMBER_COLUMN_NAME = "PHONENUMBER";
	private static final String DISCOUNT_ID_COLUMN_NAME = "DISCOUNT_ID";

	// SQL statements
	
	/**
	 * insert a customer
	 */
	private static final String INSERT_CUSTOMER_SQL = 
			"insert into " + TABLE_NAME + 
				" (" + ID_COLUMN_NAME + ", " + VAT_NUMBER_COLUMN_NAME + ", " + DESIGNATION_COLUMN_NAME + ", " +
					PHONE_NUMBER_COLUMN_NAME + ", " + DISCOUNT_ID_COLUMN_NAME + ") " +
			"values (DEFAULT, ?, ?, ?, ?)";
	/**
	 * obtain a customer by VAT
	 */
	private static final String GET_CUSTOMER_BY_VAT_NUMBER_SQL = 
			"select * " +
			"from " + TABLE_NAME + " " + 
			"where " + VAT_NUMBER_COLUMN_NAME + " = ?";

	/**
	 * obtain a customer by id
	 */
	private static final String GET_CUSTOMER_BY_ID_SQL =
			"select * " +
			"from " + TABLE_NAME + " " + 
			"where " + ID_COLUMN_NAME + " = ?";	
	
	
	/**
	 * Creates a customer table data gateway for given a data source. 
	 * 
	 * @param dataSource The data source the customer gateway interfaces with
	 */
	CustomerTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}

	// 1. interaction with the repository (an SQL database) using JDBC
	
	/**
	 * Add a new client to the database provided that its VAT number 
	 * is not in the database
	 * 
	 * @param vat The VAT number of the customer
	 * @param designation The customer's name
	 * @param phoneNumber The customer's phone number
	 * @param discountType The discount type for the customer
	 * @throws PersistenceException When there is an error interacting with the database
	 */
	public void insert (int vat, String designation, int phoneNumber, DiscountType discountType) 
			throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(INSERT_CUSTOMER_SQL)) {
			// set statement arguments
			statement.setInt(1, vat);
			statement.setString(2, designation);
			statement.setInt(3, phoneNumber);
			statement.setInt(4, discountType.ordinal());
			// execute SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Internal error adding the customer", e);
		}
	}
	
	/**
	 * Gets a client by its Id number
	 * 
	 * @param id The id of the client to search for
	 * @return The result set of the query
	 * @throws PersistenceException When there is an internal error interaction with the database
	 */
	public TableData find (int id) throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(GET_CUSTOMER_BY_ID_SQL)) {			
			// set statement arguments
			statement.setInt(1, id);
			// executes SQL
			try (ResultSet rs = statement.executeQuery()) {
				TableData td = new TableData();
				td.populate(rs);
				return td;
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining the customer by id", e);
		}
	}
	
	/**
	 * Fetches a customer given its VAT number and returns a result set with the
	 * result. Notice that it is fundamentally different from CustomerRowDataGateway
	 * that returns a CustomerRowDataGateway object, instead. 
	 * If there is some problem accessing the database a PersistenceException is 
	 * thrown. 
	 * 
	 * @param vatNumber The VAT number of the customer to fetch from the database
	 * @return The CustomerRowGateway corresponding to the customer with the vat number.
	 * @throws PersistenceException In case a database error is thrown
	 */
	public TableData findWithVATNumber (int vatNumber) throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(GET_CUSTOMER_BY_VAT_NUMBER_SQL)) {
			// set statement arguments
			statement.setInt(1, vatNumber);
			// executes SQL
			try (ResultSet rs = statement.executeQuery()) {
				TableData td = new TableData();
				td.populate(rs);
				return td;
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining the customer by VAT number", e);
		}
	}		
	
	// 2. Decode a customer row
	
	/**
	 * Reads from a result set the value of the corresponding column
     *
	 * @param r The row to get the information from
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error interacting with the database
	 */
	public int readID(Row r) throws PersistenceException{
		return r.getInt(ID_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readVatNumber(Row r) throws PersistenceException{
		return r.getInt(VAT_NUMBER_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
	 * @return the conversion of the value read from the result set
	 * @throws PersistenceException  When there is an error in the reading or conversion
	 */
	public String readDesignation(Row r) throws PersistenceException {
		return r.getString(DESIGNATION_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
	 * @return the conversion of the value read from the result set 
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public int readPhoneNumber(Row r) throws PersistenceException{
		return r.getInt(PHONE_NUMBER_COLUMN_NAME);
	}

	/**
	 * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
     * @return the conversion of the value read from the result set
	 * @throws PersistenceException When there is an error in the reading or conversion
	 */
	public DiscountType readDiscountType(Row r) throws PersistenceException {
		int dt;
		try {
			dt = r.getInt(DISCOUNT_ID_COLUMN_NAME);
			return DiscountType.values()[dt];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new PersistenceException("Internal error converting discount type", e);
		}
	}

}
