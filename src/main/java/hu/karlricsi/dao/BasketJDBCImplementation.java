package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.BasketItem;
import hu.karlricsi.entities.OrderItem;

public class BasketJDBCImplementation implements BasketDAO<BasketItem> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<BasketItem> findAsOrderId(int orderId) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT `order_items`.`food_id`,`food_name`,`order_items`.`price`,`quantity` FROM `feast`.`order_items`\n"
							+ "INNER JOIN `feast`.`menu` ON `menu`.`food_id`=`order_items`.`food_id` WHERE `order_id`=?");
			statement.setInt(1, orderId);
			ResultSet result = statement.executeQuery();
			List<BasketItem> basketItems = new ArrayList<>();
			while (result.next()) {
				basketItems.add(new BasketItem(result.getInt("food_id"), result.getString("food_name"),
						result.getDouble("price"), result.getInt("quantity")));
			}
			return basketItems;
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