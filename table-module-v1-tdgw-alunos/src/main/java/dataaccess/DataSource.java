package dataaccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstracts a database connection
 *
 * @author fmartins
 *
 */
class DataSource implements AutoCloseable {
	
	/**
	 * A connection to the database
	 */ 
	private Connection connection;

	/**
	 * an exception logger
	 */
	private static Logger log = Logger.getLogger(DataSource.class.getCanonicalName());

	// 1. Connections
	
	/**
	 * Constructs a database connection given the connection url, the username, and its password
	 * for the database engine.
	 * 
	 * @param url The database connection URL 
	 * @param username The username to login into the database 
	 * @param password The user's password 
	 * @throws PersistenceException In case the connection fails to establish
	 */
	void connect(String url, String username, String password) throws PersistenceException {
		try {
			connection = DriverManager.getConnection (url, username, password);
		} catch (SQLException e) {
			throw new PersistenceException("Cannot connect to database", e);
		}
	}
	
	/**
	 * @return The current database connection 
	 */
	Connection getConnection() {
		return connection;
	}

	/**
	 * Close the database connection
	 */
	@Override
	public void close () {
        try {
            connection.close();
        } catch (SQLException e) {
            // nothing that we can do about it apart from logging the exception
        	log.log(Level.SEVERE, "Error closing the database connection", e);
        }
	}
	
	// 2. Prepare statements

	/**
	 * Prepare an SQL statement from an SQL string
	 * 
	 * @param sql The SQL text to prepare the command
	 * @return The prepared statement for the SQL text
	 * @throws PersistenceException In case the prepare statement 
	 * encounters an error.
	 */
	PreparedStatement prepare(String sql) throws PersistenceException {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			throw new PersistenceException("Error preparing command", e);
		} 
	}
	
	/**
	 * Prepare an SQL statement from an SQL string and informs the underlying JDBC 
	 * layer to get the automatically generated database keys.
	 * 
	 * @param sql The SQL text to prepare the command
	 * @return The prepared statement for the SQL text
	 * @throws PersistenceException In case the prepare statement 
	 * encounters an error.
	 */
	PreparedStatement prepareGetGenKey(String sql) throws PersistenceException {
		try {
			return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
		} catch (SQLException e) {
			throw new PersistenceException("Error preparing command", e);
		} 
	}
		
	// 3. Transactions
	
	/**
	 * Begins a database transaction
	 * 
	 * @throws PersistenceException In case the set commit flag cannot be set
	 */
	void beginTransaction() throws PersistenceException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new PersistenceException("Error starting DB transaction", e);
		}
	}
	
	/**
	 * Commits a transaction 
	 * 
	 * @throws PersistenceException In case the commit transaction fails
	 */
	void commit() throws PersistenceException {
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new PersistenceException("Error on commit", e);
		}
		startAutoCommit();
	}

	/**
	 * Rolls back a transaction
	 * 
	 * @throws PersistenceException In case the rollback transaction fails
	 */
	void rollback() throws PersistenceException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new PersistenceException("Error on rollback!", e);
		}
		startAutoCommit();
	}

	/**
	 * Disables commitment control
	 * 
	 * @throws PersistenceException In case the set commit flag cannot be set
	 */
	private void startAutoCommit() throws PersistenceException {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new PersistenceException("Error starting auto commit", e);
		}
	}
}
