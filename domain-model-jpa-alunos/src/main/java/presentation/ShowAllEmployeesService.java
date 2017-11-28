package presentation;

import business.ShowAllEmployeesHandler;

import java.util.List;

import business.ApplicationException;
import business.Employee;

public class ShowAllEmployeesService {
	
	private ShowAllEmployeesHandler employeeHandler;

	public ShowAllEmployeesService(ShowAllEmployeesHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public List<Employee> showAllEmployees() throws ApplicationException {
		return employeeHandler.showAllEmployees();
	}
}
