package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity  
@NamedQuery(name=Employee.FIND_BY_ID_NUMBER, query="SELECT e FROM Employee e WHERE e.id = :" + 
		Employee.NUMBER_ID_NUMBER)
public class Employee {

	// Named query name constants
	public static final String FIND_BY_NIF_NUMBER = "Employee.findByNIFNumber";
	public static final String NUMBER_NIF_NUMBER = "nifNumber";
	public static final String FIND_BY_ID_NUMBER = "Employee.findByID";
	public static final String NUMBER_ID_NUMBER = "id";
	// Employee attributes 

	/**
	 * Employee primary key. Needed by JPA. 
	 */
	@Id @GeneratedValue private int id;

	/**
	 * Employee's NIF number
	 */
	@Column(nullable = false, unique = true) private int nifNumber;
	
	/**
	 * Employee's type(seller,manager,janitor, etc.)  
	 */
	@Column(nullable = false) private String designation;
	
	/**
	 * Employee's name.  
	 */
	@Column(nullable = false) private String name;
	
	/**
	 * Employee's age.  
	 */
	@Column(nullable = false) private int age;
	
	/**
	 * Employee's gender.  
	 */
	@Column(nullable = false) private String gender;
	
	/**
	 * Employee's contact number
	 */
	@SuppressWarnings("unused")
	private int phoneNumber;
	
	/**
	 * Employee's department.
	 */
	@OneToOne @JoinColumn(nullable = false) private int department_id;
	
	// 1. constructor 

	/**
	 * Constructor needed by JPA.
	 */
	Employee() {
	}

	/**
	 * Creates a new employee given its NIF number, its designation, phone contact, age, gender, department
	 * 
	 * @param nifNumber The employee NIF number
	 * @param designation The employee's designation
	 * @param phoneNumber The employee's phone number
	 * @param age The employee's age.
	 * @param gender The employee's gender
	 * @param department_id The department of the employee.
	 * @pre isValidNIF(nif) 
	 */
	public Employee(int nifNumber, String name, String designation, int phoneNumber, int age, String gender, int department_id) {
		this.nifNumber = nifNumber;
		this.name = name;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.gender = gender;
		this.department_id = department_id;
	}

	/**
	 * 
	 * @return The employee's number
	 */
	public int getNIFNumber() {
		return nifNumber;
	}
	
	/**
	 * 
	 * @return The employee's department
	 */
	public int getDepartmentId() {
		return department_id;
	}
	
	/**
	 * 
	 * @return The employee's id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Checks if a NIF number is valid.
	 * 
	 * @param nif The number to be checked.
	 * @return Whether the nif number is valid. 
	 */
	public static boolean isValidNIF(int nif) {
		// If the number of digits is not 9, error!
		if (nif < 100000000 || nif > 999999999)
			return false;
		
		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = nif / 100000000;
		if (firstDigit != 1 && firstDigit != 2 && 
			firstDigit != 5 && firstDigit != 6 &&
			firstDigit != 8 && firstDigit != 9)
			return false;
		
		// Checks the congruence modules 11.
		int sum = 0;
		int checkDigit = nif % 10;
		nif /= 10;
		
		for (int i = 2; i < 10 && nif != 0; i++) {
			sum += nif % 10 * i;
			nif /= 10;
		}
		
		int checkDigitCalc = 11 - sum % 11;
		if (checkDigitCalc == 10)
			checkDigitCalc = 0;
		return checkDigit == checkDigitCalc;
	}
}


