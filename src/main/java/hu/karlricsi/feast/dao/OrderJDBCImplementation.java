package hu.karlricsi.feast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.feast.entities.Order;
import hu.karlricsi.feast.entities.ProductConsumption;
import hu.karlricsi.feast.entities.UserComsumption;

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
	public Order find(int orderId) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`orders` WHERE `order_id`=?");
			statement.setInt(1, orderId);
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

	@Override
	public int insertAll(List<Order> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(Order order) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO `feast`.`orders` (`user_id`,`date`) VALUES (?,?)");
			statement.setInt(1, order.getUser_id());
			statement.setDate(2, order.getDate());
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
	public int update(Order order) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE `feast`.`orders` SET `user_id`=?,`date`=?,`closed`=? WHERE `order_id`=?");
			statement.setInt(1, order.getUser_id());
			statement.setDate(2, order.getDate());
			statement.setBoolean(3, order.isClosed());
			statement.setInt(4, order.getOrderId());
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
	public int delete(Order order) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM `feast`.`orders` WHERE `order_id`=?");
			statement.setInt(1, order.getOrderId());
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

	@Override
	public List<UserComsumption> getUserConsumptions() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT `sums`.`name`,SUM(`sums`.`sum`) AS `sum` FROM ("
							+ "SELECT `users`.`name`,SUM(`order_items`.`price`*`order_items`.`quantity`) AS `sum` FROM `feast`.`order_items` "
							+ "INNER JOIN `feast`.`orders`ON `orders`.`order_id`=`order_items`.`order_id` "
							+ "INNER JOIN `feast`.`users` ON `orders`.`user_id`=`users`.`user_id` "
							+ "WHERE MONTH(`orders`.`date`)=MONTH(NOW()) AND `closed`=true "
							+ "GROUP BY `order_items`.`order_id`) AS `sums` GROUP BY `sums`.`name` ORDER BY `sums`.`name`");
			ResultSet result = statement.executeQuery();
			List<UserComsumption> consumptions = new ArrayList<>();
			while (result.next()) {
				consumptions.add(new UserComsumption(result.getString("name"), result.getDouble("sum")));
			}
			return consumptions;
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
	public List<ProductConsumption> getProductConsumptions() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT `menu`.`food_name`,SUM(`order_items`.`quantity`) AS `sum` FROM `feast`.`order_items` "
							+ "INNER JOIN `feast`.`orders`ON `orders`.`order_id`=`order_items`.`order_id` "
							+ "INNER JOIN `feast`.`menu` ON `order_items`.`food_id`=`menu`.`food_id` "
							+ "WHERE MONTH(`orders`.`date`)=MONTH(NOW())  AND `closed`=true "
							+ "GROUP BY `menu`.`food_name` ORDER BY `sum` DESC,`menu`.`food_name`");
			ResultSet result = statement.executeQuery();
			List<ProductConsumption> consumptions = new ArrayList<>();
			while (result.next()) {
				consumptions.add(new ProductConsumption(result.getString("food_name"), result.getDouble("sum")));
			}
			return consumptions;
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