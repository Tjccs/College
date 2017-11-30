package business;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;

/**
 * The initial object. The first domain object built during the startup.
 * 
 * @author fmartins
 * @version 1.0 (17/04/2015)
 *
 */

@Entity
public class SaleSys {

	/**
	 * The add customer use case handler
	 */
	private AddCustomerHandler addCustomerHandler;
	
	/**
	 * The process sale use case handler 
	 */
	private ProcessSaleHandler processSaleHandler;
	
	/**
	 * The add customer use case handler
	 */
	private AddEmployeeHandler addEmployeeHandler;
	
	/**
	 * The remove employee use case handler
	 */
	private RemoveEmployeeHandler removeEmployeeHandler;
	
	/**
	 * The add department use case handler
	 */
	private AddDepartmentHandler addDepartmentHandler;
	
	/**
	 * The show all employees use case handler;
	 */
	private ShowAllEmployeesHandler showAllEmployeesHandler;
	
	/**
	 * Performs the start up use case
	 */
	public SaleSys(EntityManagerFactory emf) {
		CustomerCatalog customerCatalog = new CustomerCatalog(emf);
		EmployeeCatalog employeeCatalog = new EmployeeCatalog(emf);
		DepartmentCatalog departmentCatalog = new DepartmentCatalog(emf);
		this.addCustomerHandler = new AddCustomerHandler(customerCatalog, new DiscountCatalog(emf));
		this.processSaleHandler = new ProcessSaleHandler(new SaleCatalog(emf), customerCatalog, new ProductCatalog(emf));
		this.addEmployeeHandler = new AddEmployeeHandler(employeeCatalog, departmentCatalog);
		this.removeEmployeeHandler = new RemoveEmployeeHandler(employeeCatalog, departmentCatalog);
		this.addDepartmentHandler = new AddDepartmentHandler(departmentCatalog);
		this.showAllEmployeesHandler = new ShowAllEmployeesHandler(employeeCatalog);
	}
	
	/**
	 * @return The add customer use case handler
	 */
	public AddCustomerHandler getAddCustomerHandler () {
		return addCustomerHandler;
	}

	/**
	 * @return The process sale use case handler
	 */
	public ProcessSaleHandler getProcessSaleHandler() {
		return processSaleHandler;
	}

	/**
	 * @return The add employee use case handler
	 */
	public AddEmployeeHandler getAddEmployeeHandler () {
		return addEmployeeHandler;
	}

	/**
	 * @return The remove employee use case handler
	 */
	public RemoveEmployeeHandler getRemoveEmployeeHandler () {
		return removeEmployeeHandler;
	}

	/**
	 * @return The remove employee use case handler
	 */
	public AddDepartmentHandler getAddDepartmentHandler () {
		return addDepartmentHandler;
	}

	/**
	 * @return The show all employees use case handler
	 */
	public ShowAllEmployeesHandler getShowAllEmployeesHandler () {
		return showAllEmployeesHandler;
	}
}
