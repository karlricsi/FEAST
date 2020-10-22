package hu.karlricsi.dao;

import java.util.List;

public interface BasketDAO<T> {

	public List<T> findAsOrderId(int orderId) throws DAOException;

}
