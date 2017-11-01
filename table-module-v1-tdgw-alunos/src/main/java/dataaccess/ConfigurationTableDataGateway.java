 package dataaccess;

import dataaccess.TableData.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Table gateway for table configuration.
 * 
 * Please read the remarks in the CustomerTableGateway class
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class ConfigurationTableDataGateway extends TableDataGateway {

    // Database schema constants

	/**
	 * Table name
	 */
	private static final String TABLE_NAME = "appconfig";

	/**
     * Column labels
	 */
    private static final String AMOUNT_THRESHOLD_COLUMN_NAME = "AMOUNTTHRESHOLD";
    private static final String TOTAL_AMOUNT_PERCENTAGE_COLUMN_NAME = "TOTALAMOUNTPERCENTAGE";
    private static final String ELIGIBLE_PERCENTAGE_COLUMN_NAME = "ELIGIBLEPERCENTAGE";


	// SQL statements
    
	/**
	 * obtain the application configuration information SQL statement
	 */
	private static final String GET_APP_CONFIG_SQL = 
			"select * from " + TABLE_NAME;
	
	
	/**
	 * Creates a configuration table data gateway for given a data source. 
	 * 
	 * @param dataSource The data source the customer gateway interfaces with
	 */    
    ConfigurationTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * Gets configuration settings
	 * 
	 * @return The result set of the query
	 * @throws PersistenceException When there is an internal error accessing the database
	 */
	public TableData loadConfiguration () throws PersistenceException {
		try (PreparedStatement statement = dataSource.prepare(GET_APP_CONFIG_SQL);
				ResultSet rs = statement.executeQuery()) {
			return new TableData().populate(rs);
		} catch (SQLException e) {
			throw new PersistenceException("Internal error obtaining app configuration", e);
		}
	}

	
	// 2. Decode a customer row	

    /**
     * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
     * @return the conversion of the value read from the result set
     * @throws PersistenceException When there is an error in the reading or conversion
     */
    public double readAmountThreshold(Row r) throws PersistenceException{
        return r.getDouble(AMOUNT_THRESHOLD_COLUMN_NAME);
    }

    /**
     * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
     * @return the conversion of the value read from the result set
     * @throws PersistenceException When there is an error in the reading or conversion
     */
    public double readTotalAmountPercentage(Row r) throws PersistenceException{
        return r.getDouble(TOTAL_AMOUNT_PERCENTAGE_COLUMN_NAME);
    }

    /**
     * Reads from a result set the value of the corresponding column
     *
     * @param r The row to get the information from
     * @return the conversion of the value read from the result set
     * @throws PersistenceException When there is an error in the reading or conversion
     */
    public double readEligiblePercentage(Row r) throws PersistenceException{
        return r.getDouble(ELIGIBLE_PERCENTAGE_COLUMN_NAME);
    }
}
