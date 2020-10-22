package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.Order;

public class OrderJDBCImplementation implements OrderDAO<Order> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Order> findAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Order find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<Order> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(Order data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Order data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(Order data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Order findOpenedOrder(int userId) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM `feast`.`orders` WHERE `user_id`=? AND `date`=CURDATE() AND `closed`=FALSE LIMIT 1");
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return new Order(result.getInt("order_id"), result.getInt("user_id"), result.getDate("date"),
						result.getBoolean("closed"));
			}
			return new Order();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
	}

}