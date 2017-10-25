package business;

import java.util.Date;
import dataaccess.Persistence;
import dataaccess.PersistenceException;
import dataaccess.ProductTableDataGateway;
import dataaccess.SaleProductTableDataGateway;
import dataaccess.SaleTableDataGateway;
import dataaccess.TableData;
import dataaccess.TableData.Row;
import datatypes.SaleStatus;
import facade.exceptions.ApplicationException;

public class SaleModule extends TableModule {

	private SaleTableDataGateway table;
	private SaleProductTableDataGateway table2;
	private ProductTableDataGateway table3;
	
	
	/**
	 * Constructs a sale module given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public SaleModule (Persistence persistence) {
		super(persistence);
		table = persistence.saleTableGateway;
		table2 = persistence.saleProductTableGateway;
		table3 = persistence.productTableGateway;
	}
	
	/**
	 * Add a new sale.
	 * Notice the interaction with the Customer module. We use the
	 * customer module to get the customer id of the customer with
	 * a given VAT number. 
	 * 
	 * @param vat The VAT number of the customer the sale belongs to
	 * @return The internal sale id so that products may be added to the sale
	 * @throws ApplicationException When there is no customer with the given
	 * VAT number or when there is an unexpected error add the sale.
	 */
	public int newSale (int vat) throws ApplicationException {
		int custId = new CustomerModule(persistence).getCustomerId(vat);
		Date date = new Date();
		int id = 0;
		try {
			id = table.insert(date, 0, 0, SaleStatus.OPEN, custId);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * @param saleId The sale id to check if it is closed
	 * @return If the sale is closed
	 * @throws ApplicationException When the sale does not exist or some obscure
	 * error has occurred.
	 */
	public boolean isClosed(int saleId) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(saleId);
			SaleStatus ts = null;
			if (!td.isEmpty()) {
				ts = table.readStatus(td.iterator().next());
				return (ts.equals(SaleStatus.CLOSED)) ? true : false;
			} else {
				throw new ApplicationException("Internal error obtaining sale with id " + saleId + ".");
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException ("Internal error obtaining sale with id " + saleId + ".", e);
		}
		
	}
	
	/**
	 * Add a product to a sale.
	 * Mind the usage of the Product module to get the product id from the product code
	 * and to further update its stock existence.
	 * 
	 * @param saleId The sale id to add a product to
	 * @param productCode The product to be added to the sale
	 * @param qty The quantity sold of the product
	 * @throws ApplicationException When the sale is closed, the product code does not
	 * exist or some internal error occurred while saving the data.
	 */
	public void addProductToSale(int saleId, int productCode, double qty) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(saleId);
			ProductModule pM = new ProductModule(persistence);
			
			SaleStatus ts;
			if (!td.isEmpty()) {
				
				ts = table.readStatus(td.iterator().next());
				
				if (ts.equals(SaleStatus.OPEN)) {
					
					
					int prodCode = pM.getProductId(productCode);
				
					
					
					TableData td2 = table3.find(prodCode);
					System.out.println(prodCode);
					
					
					if (table2.readQuantity(td2.iterator().next()) >= qty) {
						table2.insert(saleId, prodCode, qty);
						pM.takeFromStock(prodCode, qty);
					}  else {
						throw new ApplicationException("Internal error obtaining sale with id " + saleId + ".");
					}
				}
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining sale with id " + saleId + ".", e);
		}
	}
	
	/**
	 * @param saleId The sale to compute the discount.
	 * @return Compute the discount of the sale. 
	 * @throws ApplicationException When some referential integrity problem occurs. This
	 * might happen if a foreign key is not found, for instance.
	 */
	public double getSaleDiscount (int saleId) throws ApplicationException {
		// TODO: program me!
		try {
			TableData td = table.find(saleId);
			if (!td.isEmpty()) {
				Row firstRow = td.iterator().next();
				return table.readDiscount(firstRow);
			} else {
				throw new ApplicationException("Internal error obtaining sale with id " + saleId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining sale with id " + saleId + ".", e);
		}
	}

	
	/**
	 * @param saleId The sale to get the customer id from
	 * @return The customer id of the sale
	 * @throws ApplicationException In case an error occurs when retrieving the
	 * information from the database.
	 */
	public int getCustomerId (int saleId) throws ApplicationException {
		try {
			TableData td = table.find(saleId);
			if (!td.isEmpty()) {
				return table.readCustomerId(td.iterator().next());
			} else {
				throw new ApplicationException("Internal error obtaining sale with id " + saleId + ".");
			}
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining sale with id " + saleId + ".", e);
		}
	}
}
