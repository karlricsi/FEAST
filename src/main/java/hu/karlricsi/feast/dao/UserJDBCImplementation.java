package hu.karlricsi.feast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import hu.karlricsi.feast.entities.User;

public class UserJDBCImplementation implements DAO<User> {

	@Autowired
	private DataSource dataSource;
	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<User> findAll() throws DAOException {
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `feast`.`users` ORDER BY `name`");
			ResultSet result = statement.executeQuery();
			List<User> users = new ArrayList<>();
			while (result.next()) {
				users.add(new User(result.getInt("user_id"), result.getString("name")));
			}
			return users;
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
	public User find(int id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insertAll(List<User> list) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(User data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(User data) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(User data) throws DAOException {
		throw new UnsupportedOperationException();
	}

}