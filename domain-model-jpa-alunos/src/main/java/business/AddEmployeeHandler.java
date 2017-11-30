package business;

public class AddEmployeeHandler {
	
	/**
	 * The employee's catalog
	 */
	private EmployeeCatalog employeeCatalog;
	
	/**
	 * The department's catalog 
	 */
	private DepartmentCatalog departmentCatalog;

	/**
	 * Creates a handler for the add employee use case given
	 * the employee and the department catalogs.
	 *  
	 * @param employeeCatalog A customer's catalog
	 * @param departmentCatalog A discount's catalog
	 */
	public AddEmployeeHandler(EmployeeCatalog employeeCatalog, DepartmentCatalog departmentCatalog) {
		this.employeeCatalog = employeeCatalog;
		this.departmentCatalog = departmentCatalog;
	}
	
	/**
	 * Adds a new employee with a valid Number. It checks that there is no other 
	 * employee in the database with the same NIF.
	 * 
	 * @param nif the NIF of the Employee
	 * @param denomination The employee's job.
	 * @param phoneNumber The employee's phone 
	 * @param age The Employee's age.
	 * @param gender The Employees gender.
	 * @param department_id The employee's department.
	 * @throws ApplicationException When the NIF number is invalid (we check it according 
	 * to the Portuguese legislation).
	 */
	public void addEmployee (int nif, String name, String denomination, 
			int phoneNumber, int age, String gender, int department_id) 
			throws ApplicationException {
		//Department department = departmentCatalog.getDepartment(department_id);
		employeeCatalog.addEmployee(nif, name, denomination, phoneNumber, age, gender, department_id);
	}
}
