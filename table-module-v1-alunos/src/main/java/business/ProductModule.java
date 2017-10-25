package business;

import dataaccess.Persistence;
import dataaccess.PersistenceException;
import dataaccess.ProductTableDataGateway;
import dataaccess.TableData;
import datatypes.DiscountType;
import facade.exceptions.ApplicationException;

/**
 * A table module for products.
 * See remarks in the Customer class.
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class ProductModule extends TableModule {

	private ProductTableDataGateway table;

	/**
	 * Constructs a product module given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public ProductModule(Persistence persistence) {
		super(persistence);
		table = persistence.productTableGateway;
	}


	/**
	 * @param productCode The code of the product to get its internal id
	 * @return The internal id of the product with code productCode
	 * @throws ApplicationException When the product with code productCode is not found
	 * or when there is an internal database error (not present in the current implementation
	 * of the in memory database with use).
	 */
	public int getProductId (int productCode) throws ApplicationException {
		// TODO: program me!
		try {
			
			TableData td = table.findByProdCod(productCode);
			
			if (!td.isEmpty()) {
				
				return table.readId(td.iterator().next());
			} else {
				throw new ApplicationException("Internal error obtaining product with code " + productCode + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining product with code " + productCode + ".", e);
		}
	}

	/**
	 * Reduce by qty the number of units in stock of product with id productId.
	 * 
	 * @param productId The product id to reduce the units in stock
	 * @param qty The number of units to remove from stock of productId
	 * @throws ApplicationException When there is not enough products in stock to fulfill
	 * the request or refer to getDoubleFieldFromProduct for the other possible exceptions. 
	 */
	public void takeFromStock (int productId, double qty) throws ApplicationException {
		// TODO: program me!
		try {
			
			TableData td = table.find(productId);
			if (!td.isEmpty()) {
				table.updateStock(productId, --qty);
				
			} else {
				throw new ApplicationException("Internal error obtaining product with id " + productId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining product with id " + productId + ".", e);
		}
	}

	public boolean isEligibleForDiscount(int productId) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(productId);
			boolean iSE;
			if (!td.isEmpty()) {
				iSE = table.readDiscountEligibility(td.iterator().next());
				return iSE;
			} else {
				throw new ApplicationException("Internal error obtaining product with id " + productId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining product with id " + productId + ".", e);
		}
	}

	/**
	 * @param productId The id of the product to get its stock quantity
	 * @return The quantity in stock of the product with id productId
	 * @throws ApplicationException When the product with productId does not exist or
	 * when there is an internal database error (which is not the case in this example),
	 * nevertheless, the ResultSet is prepared to send such an exception.
	 */
	public double getQty(int productId) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(productId);
			if (!td.isEmpty()) {
				return table.readQuantity(td.iterator().next());
			} else {
				throw new ApplicationException("Internal error obtaining product with id " + productId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining product with id " + productId + ".", e);
		}
	}
	
	public double getFaceValue(int productId) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(productId);
			if (!td.isEmpty()) {
				return table.readFaceValue(td.iterator().next());
			} else {
				throw new ApplicationException("Internal error obtaining product with id " + productId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining product with id " + productId + ".", e);
		}
	}

}
