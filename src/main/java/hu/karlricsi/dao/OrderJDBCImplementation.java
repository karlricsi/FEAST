package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.Month;
import hu.karlricsi.entities.Order;
import hu.karlricsi.entities.Year;

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
	public List<Year> findYearsContainOrders() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT YEAR(`date`) as `year` FROM `feast`.`orders` GROUP BY `year` ORDER BY `year`");
			ResultSet result = statement.executeQuery();
			List<Year> years = new ArrayList<>();
			while (result.next()) {
				years.add(new Year(result.getInt("year")));
			}
			return years;
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
	public List<Month> findMonthsContainOrders(Year year) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT MONTH(`date`) as `month` FROM `feast`.`orders` WHERE YEAR(`date`)=? GROUP BY `month` ORDER BY `month`");
			statement.setInt(1, year.getYear());
			ResultSet result = statement.executeQuery();
			List<Month> months = new ArrayList<>();
			while (result.next()) {
				months.add(new Month(result.getInt("month")));
			}
			return months;
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