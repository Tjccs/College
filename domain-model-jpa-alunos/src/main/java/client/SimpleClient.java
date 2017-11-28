package client;

import presentation.AddCustomerService;
import presentation.ProcessSaleService;
import presentation.AddEmployeeService;
import presentation.RemoveEmployeeService;
import presentation.ShowAllEmployeesService;
import presentation.AddDepartmentService;
import business.ApplicationException;

/**
 * A simple application client that uses both application handlers.
 *	
 * @author fmartins
 * @version 1.1 (17/04/2015)
 */
public class SimpleClient {

	private AddCustomerService addCustomerService;
	private ProcessSaleService processSaleService;
	private AddEmployeeService addEmployeeService;
	private AddDepartmentService addDepartmentService;
	private RemoveEmployeeService removeEmployeeService;
	private ShowAllEmployeesService showAllEmployeesService;
	
	public SimpleClient(AddCustomerService addCustomerService,
			ProcessSaleService processSaleService, AddEmployeeService addEmployeeService, AddDepartmentService addDepartmentService,
			RemoveEmployeeService removeEmployeeService, ShowAllEmployeesService showAllEmployeesService) {
		this.addCustomerService = addCustomerService;
		this.processSaleService = processSaleService;
		this.addEmployeeService = addEmployeeService;
		this.addDepartmentService = addDepartmentService;
		this.removeEmployeeService = removeEmployeeService;
		this.showAllEmployeesService = showAllEmployeesService;
	}
	
	/**
	 * A simple interaction with the application services
	 */
	public void createASale() {
		
		// the interaction
		try {
			// adds a customer, adds a department and adds an employee.
			addCustomerService.addCustomer(168027852, "Customer 1", 217500255, 2);
			addDepartmentService.addDepartment("rh", 210000000);
			addEmployeeService.addEmployee(999999999, "Employee 1", "Manager", 910000000, 32, "Male", 1);
			
			// show all employees
			System.out.println(showAllEmployeesService.showAllEmployees());
			
			// starts a new sale
			processSaleService.newSale(168027852);

			// adds two products to the sale
			processSaleService.addProductToSale(123, 6);
			processSaleService.addProductToSale(124, 5);
			processSaleService.addProductToSale(123, 4);

			// gets the discount amount
			System.out.println(processSaleService.getSaleDiscount());
		} catch (ApplicationException e) {
			System.out.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application
			// this information can be associated with a "details" button when
			// the error message is displayed.
			if (e.getCause() != null) 
				System.out.println("Cause: ");
			e.printStackTrace();
		}
	}
}
