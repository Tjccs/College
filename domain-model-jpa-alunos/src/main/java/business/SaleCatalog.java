package business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * A catalog for sales
 * 
 * @author fmartins
 * @version 1.1 (17/04/2015)
 *
 */

@Entity
public class SaleCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructs a sale's catalog giving a entity manager factory
	 */
	public SaleCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Creates a new sale and adds it to the repository
	 * 
	 * @param customer The customer the sales belongs to
	 * @return The newly created sale
	 */
	public Sale newSale (Customer customer) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		
		Date date = new Date();
		try {
			em.getTransaction().begin();
			Sale sale = new Sale(date, customer);
			em.persist(sale);	
			em.getTransaction().commit();
			return sale;
		} catch (PersistenceException e) {
			throw new ApplicationException ("Customer not found.");
		} finally {
			em.close();
		} 
	}

	/**
	 * @param sale
	 * @param product
	 * @param qty
	 * @throws ApplicationException
	 */
	public Sale addProductToSale (Sale sale, Product product, double qty) 
			throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			
			
			sale.addProductToSale(product, qty);
			em.getTransaction().commit();
			return sale;
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding product", e);
		} finally {
			em.close();
		}

	}
}


