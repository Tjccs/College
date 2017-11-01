package dataaccess;

import java.sql.Connection;

public class Persistence implements AutoCloseable {

	private DataSource dataSource;
	public final ConfigurationTableDataGateway configurationTableGateway;
	public final CustomerTableDataGateway customerTableGateway;
	public final ProductTableDataGateway productTableGateway;
	public final SaleTableDataGateway saleTableGateway;
	public final SaleProductTableDataGateway saleProductTableGateway;
	
	public Persistence(String url, String username, String password) throws PersistenceException {
		dataSource = new DataSource();
		dataSource.connect(url, username, password);
		configurationTableGateway = new ConfigurationTableDataGateway (dataSource);
		customerTableGateway = new CustomerTableDataGateway (dataSource);
		productTableGateway = new ProductTableDataGateway(dataSource);
		saleTableGateway = new SaleTableDataGateway(dataSource);
		saleProductTableGateway = new SaleProductTableDataGateway(dataSource);
	}

	@Override
	public void close()  {
		dataSource.close();
	}
	
    /**
     * @return The current database connection
     */
    public Connection getConnection() {
        return dataSource.getConnection();
    }

	public void beginTransaction() throws PersistenceException {
		dataSource.beginTransaction();
	}
	
	public void commit() throws PersistenceException {
		dataSource.commit();
	}
	
	public void rollback() throws PersistenceException {
		dataSource.rollback();
	}
}
