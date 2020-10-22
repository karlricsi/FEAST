package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.Food;

public class FoodJDBCImplementation implements DAO<Food> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Food> findAll() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`menu` ORDER BY `food_id`");
			ResultSet result = statement.executeQuery();
			List<Food> foods = new ArrayList<>();
			while (result.next()) {
				foods.add(new Food(result.getInt("food_id"), result.getInt("category_id"),
						result.getString("food_name"), result.getDouble("price")));
			}
			return foods;
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
	public Food find(int id) throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`menu` WHERE `food_id`=? LIMIT 1");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			return result.next()
					? new Food(result.getInt("food_id"), result.getInt("category_id"), result.getString("food_name"),
							result.getDouble("price"))
					: new Food();
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
	public int insertAll(List<Food> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(Food data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Food data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(Food data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}