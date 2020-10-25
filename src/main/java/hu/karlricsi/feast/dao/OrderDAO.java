package hu.karlricsi.feast.dao;

import java.util.List;
import hu.karlricsi.feast.entities.UserComsumption;

public interface OrderDAO<T> extends DAO<T> {

	public T findOpenedOrder(int userId) throws DAOException;

	public List<UserComsumption> getUserConsumptions() throws DAOException;

}