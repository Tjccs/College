package business;

public class AddDepartmentHandler {
	
	/**
	 * The department's catalog 
	 */
	private DepartmentCatalog departmentCatalog;

	/**
	 * Creates a handler for the add department use case given
	 * the department catalogs.
	 * @param departmentCatalog A discount's catalog
	 */
	public AddDepartmentHandler(DepartmentCatalog departmentCatalog) {
		this.departmentCatalog = departmentCatalog;
	}
	
	public void addEmployeeToDepartment(int department_id, Employee employee) 
			throws ApplicationException {
		departmentCatalog.addEmployeeToDepartment(department_id, employee);
	}

	public void newDepartment(String name, int phoneNumber) throws ApplicationException {
		departmentCatalog.newDepartment(name, phoneNumber);
	}
}
