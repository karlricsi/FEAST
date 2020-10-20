package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.MenuElement;

public class MenuElementJDBCImplementation implements DAO<MenuElement> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<MenuElement> findAll() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`menu` ORDER BY `food_id`");
			ResultSet result = statement.executeQuery();
			List<MenuElement> foods = new ArrayList<>();
			while (result.next()) {
				foods.add(new MenuElement(result.getInt("food_id"), result.getInt("category_id"),
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
	public MenuElement find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<MenuElement> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(MenuElement data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(MenuElement data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(MenuElement data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
