package business;

public class RemoveEmployeeHandler {
	
	/**
	 * The employee's catalog
	 */
	private EmployeeCatalog employeeCatalog;
	
	/**
	 * The department's catalog 
	 */
	private DepartmentCatalog departmentCatalog;
	
	/**
	 * Creates a handler for the remove employee use case given
	 * the employee and the department catalogs.
	 *  
	 * @param employeeCatalog A employee's catalog
	 * @param departmentCatalog A department's catalog
	 */
	public RemoveEmployeeHandler(EmployeeCatalog employeeCatalog, DepartmentCatalog departmentCatalog) {
		this.employeeCatalog = employeeCatalog;
		this.departmentCatalog = departmentCatalog;
	}

	/**
	 * Removes the employee given it's id number, since it's unique.
	 * 
	 * 
	 * @param id the id of the employee
	 * @throws ApplicationException When the id number is invalid (not in the database).
	 */
	public void RemoveEmployee (int id) throws ApplicationException {
		employeeCatalog.removeEmployee(id);
		departmentCatalog.removeFromDepartment(id);
	}

}
