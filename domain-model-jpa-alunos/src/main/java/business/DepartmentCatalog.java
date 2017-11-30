package business;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@Entity
public class DepartmentCatalog {

	EmployeeCatalog emp;
	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Constructs a department's catalog giving a entity manager factory
	 */
	public DepartmentCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Creates a new department and adds it to the repository
	 * 
	 * @param The name of the department
	 * @param The departments phone number.
	 * @return The newly created department
	 */
	public Department newDepartment (String name, int phoneNumber) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Department department = new Department(name, phoneNumber);
			em.persist(department);	
			em.getTransaction().commit();
			return department;
		} catch (PersistenceException e) {
			throw new ApplicationException ("Department already exists.");
		} finally {
			em.close();
		} 
	}

	/**
	 * @param department_id
	 * @param employee to add
	 * @throws ApplicationException
	 */
	public Department addEmployeeToDepartment (int department_id, Employee employee) 
			throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Department d = getDepartment(department_id);
			d.addEmployeeToDepartment(employee);
			em.getTransaction().commit();
			return d;
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding employee", e);

		} finally {
			em.close();
		}

	}

	/**
	 * 
	 * @param id The id of the department
	 * @return The department that has the id from the param
	 * @throws ApplicationException
	 */
	public Department getDepartment (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Department> query = em.createNamedQuery(Department.FIND_BY_ID, Department.class);
		query.setParameter(Department.NUMBER_ID_NUMBER, id);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Department not found.");
		} finally {
			em.close();
		}

	}
	
	/**
	 * 
	 * @param id The department's id.
	 * @throws ApplicationException
	 */
	public void removeFromDepartment (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Employee emp_id = emp.getEmployee(id);
			Department d = getDepartment(emp_id.getDepartmentId());
			d.removeEmployee(id);
			em.getTransaction().commit();
		} catch (Exception e) {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
				throw new ApplicationException("Error removing employee", e);
		} finally {
			em.close();
		}
	}
}
