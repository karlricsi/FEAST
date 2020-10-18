package hu.karlricsi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mysql.cj.jdbc.MysqlDataSource;
import hu.karlricsi.dao.UserJDBCImplementation;

@Configuration
public class FeastConfiguration {

	@Bean
	public UserJDBCImplementation getUserDAO() {
		return new UserJDBCImplementation();
	}

	@Bean
	public MysqlDataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(
				"jdbc:mysql://localhost:3306/?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Europe/Budapest");
		dataSource.setUser("root");
		dataSource.setPassword("Kr17605yxdw");
		return dataSource;
	}

}
