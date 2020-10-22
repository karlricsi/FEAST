package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public int insert(OrderItem orderItem) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO `feast`.`order_items` (`order_id`,`food_id`,`price`,`quantity`) VALUES (?,?,?,?)");
			statement.setInt(1, orderItem.getOrderId());
			statement.setInt(2, orderItem.getFoodId());
			statement.setDouble(3, orderItem.getPrice());
			statement.setInt(4, orderItem.getQuantity());
			return statement.executeUpdate();
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

	@Override
	public int update(OrderItem orderItem) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE `feast`.`order_items` SET `price`=?, `quantity`=? WHERE `order_id`=? AND `food_id`=?");
			statement.setDouble(1, orderItem.getPrice());
			statement.setInt(2, orderItem.getQuantity());
			statement.setInt(3, orderItem.getOrderId());
			statement.setInt(4, orderItem.getFoodId());
			return statement.executeUpdate();
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

	@Override
	public int delete(OrderItem orderItem) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM `feast`.`order_items` WHERE `order_id`=? AND `food_id`=?");
			statement.setInt(1, orderItem.getOrderId());
			statement.setInt(2, orderItem.getFoodId());
			return statement.executeUpdate();
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