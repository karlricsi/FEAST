package hu.karlricsi.dao;

public interface OrderDAO<T> extends DAO<T> {

	public T findOpenedOrder(int userId) throws DAOException;

}
