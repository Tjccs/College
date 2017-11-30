package presentation;

import business.ApplicationException;
import business.AddDepartmentHandler;

public class AddDepartmentService {
	
	private AddDepartmentHandler departmentHandler;

	public AddDepartmentService(AddDepartmentHandler departmentHandler) {
		this.departmentHandler = departmentHandler;
	}
	
	public void addDepartment(String name, int phoneNumber) throws ApplicationException {
		departmentHandler.newDepartment(name, phoneNumber);
	}
}
