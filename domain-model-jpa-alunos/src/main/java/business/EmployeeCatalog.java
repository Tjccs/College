package business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@Entity
public class EmployeeCatalog {
	
	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Constructs a employee's catalog giving a entity manager factory
	 */
	public EmployeeCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * Finds a employee given its NIF number.
	 * 
	 * @param nif The NIF number of the employee to fetch from the repository
	 * @return The Employee object corresponding to the employee with the nif number.
	 * @throws ApplicationException When the employee with the nif number is not found.
	 */
	public Employee getEmployee (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_BY_ID_NUMBER, Employee.class);
		query.setParameter(Employee.NUMBER_ID_NUMBER, id);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("employee not found.");
		} finally {
			em.close();
		}
	}

	/**
	 * Adds a new employee
	 * 
	 * @param nif The employee's NIF number
	 * @param designation The employee's designation
	 * @param phoneNumber The employee's phone number
	 * @param age The employee's age.
	 * @param gender The employee's gender
	 * @param department_id The employee's department.
	 * @throws ApplicationException When the employee is already in the repository or the 
	 * nif number is invalid.
	 */
	public void addEmployee (int nifNumber, String name, String designation, int phoneNumber, int age, String gender, int department_id) 
			throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			department_id = em.merge(department_id);
			Employee d = new Employee(nifNumber, name, designation, phoneNumber, age, gender, department_id);
			em.persist(d);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding employee", e);
		} finally {
			em.close();
		}
	}

	/**
	 * Remove an employee
	 * 
	 * @param id The employee's id
	 * @throws ApplicationException
	 */
	public void removeEmployee(int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		DepartmentCatalog dC = null;
		try {
			em.getTransaction().begin();
			Employee emp = getEmployee(id);
			int department_id = emp.getDepartmentId();
			dC.removeFromDepartment(id);
			em.remove(emp);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding employee", e);
		} finally {
			em.close();
		}
	}
	
	/**
	 * Show all the employees's
	 * 
	 * @return A list with all employees
	 * @throws ApplicationException
	 */
	public List<Employee> showAllEmployees() throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Employee> q = em.createQuery("select e from Employee e", Employee.class);
			return q.getResultList();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding employee", e);
		} finally {
			em.close();
		}
	}
}
