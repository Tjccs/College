package presentation;

import business.AddEmployeeHandler;
import business.ApplicationException;

public class AddEmployeeService {
	
	private AddEmployeeHandler employeeHandler;

	public AddEmployeeService(AddEmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public void addEmployee(int nif, String name, String denomination, 
			int phoneNumber, int age, String gender, int department_id) throws ApplicationException {
		employeeHandler.addEmployee(nif, name, denomination, phoneNumber, age, gender, department_id);
	}
}
