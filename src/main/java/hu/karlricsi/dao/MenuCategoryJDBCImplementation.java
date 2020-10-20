package hu.karlricsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import hu.karlricsi.entities.MenuCategory;

public class MenuCategoryJDBCImplementation implements DAO<MenuCategory> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<MenuCategory> findAll() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`menu_categories` ORDER BY `category_id`");
			ResultSet result = statement.executeQuery();
			List<MenuCategory> categories = new ArrayList<>();
			while (result.next()) {
				categories.add(new MenuCategory(result.getInt("category_id"), result.getString("category_name")));
			}
			return categories;
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
	public MenuCategory find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<MenuCategory> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(MenuCategory data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(MenuCategory data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(MenuCategory data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
