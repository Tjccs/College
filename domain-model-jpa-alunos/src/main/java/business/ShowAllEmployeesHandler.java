package business;

import java.util.List;

public class ShowAllEmployeesHandler {
	
	/**
	 * The employee's catalog
	 */
	private EmployeeCatalog employeeCatalog;

	/**
	 * Creates a handler for the show all employees use case;
	 *  
	 * @param employeeCatalog A employee's catalog
	 */
	public ShowAllEmployeesHandler(EmployeeCatalog employeeCatalog) {
		this.employeeCatalog = employeeCatalog;
	}

	public List<Employee> showAllEmployees() throws ApplicationException {
		return employeeCatalog.showAllEmployees();
	}
}
