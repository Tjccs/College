package business;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * A catalog of products
 * 
 * @author fmartins
 * @version 1.1 (17/04/2015)
 *
 */
@Entity
public class ProductCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructs a discount's catalog giving a entity manager factory
	 */
	public ProductCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Finds a product given its code
	 * 
	 * @param prodCod The code of the product to find
	 * @return The product associated with the product code
	 * @throws ApplicationException When the product with a given prodCod is not found
	 */
	public Product getProduct (int prodCod) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_BY_PRODCOD, Product.class);
		query.setParameter(Product.NUMBER_PRODCOD, prodCod);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Product not found.");
		} finally {
			em.close();
		}
	}	
}
