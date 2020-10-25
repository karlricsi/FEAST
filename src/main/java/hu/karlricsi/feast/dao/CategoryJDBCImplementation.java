package hu.karlricsi.feast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import hu.karlricsi.feast.entities.Category;

public class CategoryJDBCImplementation implements DAO<Category> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Category> findAll() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `feast`.`menu_categories` ORDER BY `category_id`");
			ResultSet result = statement.executeQuery();
			List<Category> categories = new ArrayList<>();
			while (result.next()) {
				categories.add(new Category(result.getInt("category_id"), result.getString("category_name")));
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
	public Category find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<Category> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(Category data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Category data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(Category data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}