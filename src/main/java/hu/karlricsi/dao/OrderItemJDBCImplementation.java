package hu.karlricsi.dao;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.OrderItem;

public class OrderItemJDBCImplementation implements DAO<OrderItem> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<OrderItem> findAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderItem find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<OrderItem> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(OrderItem data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(OrderItem data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(OrderItem data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}