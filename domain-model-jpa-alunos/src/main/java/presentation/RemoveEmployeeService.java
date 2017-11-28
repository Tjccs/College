package presentation;

import business.RemoveEmployeeHandler;
import business.ApplicationException;

public class RemoveEmployeeService {
	
	private RemoveEmployeeHandler employeeHandler;

	public RemoveEmployeeService(RemoveEmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public void removeEmployee(int id) throws ApplicationException {
		employeeHandler.RemoveEmployee(id);
	}
}
