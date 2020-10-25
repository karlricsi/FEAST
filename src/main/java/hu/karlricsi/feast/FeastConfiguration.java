package hu.karlricsi.feast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mysql.cj.jdbc.MysqlDataSource;

import hu.karlricsi.feast.dao.*;

@Configuration
public class FeastConfiguration {

	@Bean
	public UserJDBCImplementation getUserDAO() {
		return new UserJDBCImplementation();
	}

	@Bean
	public CategoryJDBCImplementation getCategoriesDAO() {
		return new CategoryJDBCImplementation();
	}

	@Bean
	public FoodJDBCImplementation getFoodsDAO() {
		return new FoodJDBCImplementation();
	}

	@Bean
	public OrderJDBCImplementation getOrdersDAO() {
		return new OrderJDBCImplementation();
	}

	@Bean
	public OrderItemJDBCImplementation getOrderItemsDAO() {
		return new OrderItemJDBCImplementation();
	}

	@Bean
	public BasketJDBCImplementation getBasketDAO() {
		return new BasketJDBCImplementation();
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