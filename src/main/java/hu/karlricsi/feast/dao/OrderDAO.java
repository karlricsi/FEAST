package hu.karlricsi.feast.dao;

import java.util.List;
import hu.karlricsi.feast.entities.UserComsumption;
import hu.karlricsi.feast.entities.ProductConsumption;

public interface OrderDAO<T> extends DAO<T> {

	public T findOpenedOrder(int userId) throws DAOException;

	public List<UserComsumption> getUserConsumptions() throws DAOException;

	public List<ProductConsumption> getProductConsumptions() throws DAOException;

}