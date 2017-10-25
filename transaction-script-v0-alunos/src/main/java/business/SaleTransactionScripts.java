package business;

import java.util.Optional;

import dataaccess.CustomerRowDataGateway;
import dataaccess.SaleRowDataGateway;
import facade.exceptions.ApplicationException;

public class SaleTransactionScripts {

	public void addSale (int vat) throws ApplicationException {
		
		Optional<CustomerRowDataGateway> customer = CustomerRowDataGateway.getCustomerByVATNumber(vat);
		
		if (CustomerRowDataGateway.getCustomerByVATNumber(vat).isPresent()){
			//SaleRowDataGateway newSale = new SaleRowDataGateway(); 
			
		
		
		newSale.insert();
		}
	}

}
