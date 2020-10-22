package hu.karlricsi.dao;

import java.util.List;
import hu.karlricsi.entities.AddOrRemoveFood;

public interface BasketDAO<T> {

	public List<T> findAsOrderId(int orderId) throws DAOException;

	public int udateBasketItem(AddOrRemoveFood food, int orderId, double price, boolean remove) throws DAOException;

}