package hu.karlricsi.dao;

import java.util.List;

public interface DAO<T> {

	public List<T> findAll() throws DAOException;

	public T find(int id) throws DAOException;

	public int insertAll(List<T> list) throws DAOException;

	public int insert(T data) throws DAOException;

	public int update(T data) throws DAOException;

	public int delete(T data) throws DAOException;

}
