package hu.karlricsi.dao;

import java.util.List;
import hu.karlricsi.entities.Month;
import hu.karlricsi.entities.Year;

public interface OrderDAO<T> extends DAO<T> {

	public T findOpenedOrder(int userId) throws DAOException;

	public List<Year> findYearsContainOrders() throws DAOException;

	public List<Month> findMonthsContainOrders(Year year) throws DAOException;

}