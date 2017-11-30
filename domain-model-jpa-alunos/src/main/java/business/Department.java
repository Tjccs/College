package business;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name=Department.FIND_BY_ID, query="SELECT d FROM Department d WHERE d.id = :" + 
		Department.NUMBER_ID_NUMBER)
public class Department {
	
	// Named query name constants
	public static final String FIND_BY_ID = "Department.findById";
	public static final String NUMBER_ID_NUMBER = "idNumber";
	
	@Id @GeneratedValue private int id;
	
	/**
	 * The Employee's of the department
	 */
	@JoinColumn(nullable = false) private List<Employee> employees;
	
	/**
	 * Department's name.  
	 */
	@Column(nullable = false) private String name;
	
	/**
	 * Department's contact number
	 */
	@SuppressWarnings("unused")
	private int phoneNumber;
	
	/**
	 * Constructor needed by JPA.
	 */
	Department() {
	}

	/**
	 * Creates a new department given it's name,  phone contact, and employee's.
	 * 
	 * @param name The department's name.
	 * @param phoneNumber The department's phone number.
	 */
	public Department(String name, int phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.employees = new LinkedList<Employee>();
	}

	/**
	 * 
	 * @return employees The employees of that department.
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * Adds a employee to a department
	 * 
	 * @param employee The employee to add.
	 * @throws ApplicationException 
	 */
	public void addEmployeeToDepartment(Employee employee) 
			throws ApplicationException {
		employees.add(employee);
	}
	
	/**
	 * Removes an employee from a department.
	 * 
	 * @param id the id of the employee.
	 * @throws ApplicationException
	 */
	public void removeEmployee(int id) throws ApplicationException {
		for (Employee e : employees) {
			if (e.getId() == id) {
				employees.remove(e);
			}
		}
	}
}
