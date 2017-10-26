package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * An in-memory representation of a row gateway for the products composing a sale.	
 * 
 * @author 
 * @version 
 *
 */
public class SaleProductRowDataGateway {
	
	// 1. Sale product attributes 
	
	private static int id;
	private int productId;
	private double qty;
	
	/**
	 * The insert product in a sale SQL statement
	 */
	private static final String INSERT_PRODUCT_SALE_SQL = 
			"insert into SALEPRODUCT (id, productId, qty) " +
					"values (DEFAULT, ?, ?)";  // TODO: program me!

	
	/**
	 * The select the products of a sale by sale Id SQL statement
	 */
	private static final String GET_SALE_PRODUCTS_SQL = 
			"select productId from SALEPRODUCT where productId = ?"; // TODO: program me!		

	// 2. constructor


	// 3. getters and setters
	public int getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}

	public double getQty() {
		// TODO Auto-generated method stub
		return qty;
	}

	// 4. interaction with the repository (a relational database in this simple example)

	public SaleProductRowDataGateway(int id, int productId, double qty) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this. productId = productId;
		this.qty = qty;
	}

	/**
	 * Inserts the record in the products sale 
	 */
	public void insert () throws PersistenceException {
		// TODO: program me!
		try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_PRODUCT_SALE_SQL)) {
			// set statement arguments
			statement.setInt(1, id);
			statement.setInt(2, productId);
			statement.setDouble(3, qty);
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
	 * Gets the products of a sale by its sale id 
	 * 
	 * @param saleId The sale id to get the products of
	 * @return The set of products that compose the sale
	 * @throws PersistenceException When there is an error obtaining the
	 *         information from the database.
	 */
	public static Set<SaleProductRowDataGateway> findSaleProducts (int saleId) throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_SALE_PRODUCTS_SQL)) {			
			// set statement arguments
			statement.setInt(1, saleId);
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
	private static Set<SaleProductRowDataGateway> loadSale(ResultSet rs) throws RecordNotFoundException {
		Set<SaleProductRowDataGateway> nSP= new HashSet<SaleProductRowDataGateway>();
		try {
			while(rs.next()) {
				SaleProductRowDataGateway newSaleProduct = new SaleProductRowDataGateway(rs.getInt("id"), rs.getInt("productId"), rs.getDouble("qty"));
				newSaleProduct.id = rs.getInt("id");
				nSP.add(newSaleProduct);
			}
			return nSP;
		} catch (SQLException e) {
			throw new RecordNotFoundException ("Customer does not exist", e);
		}
	}


}
