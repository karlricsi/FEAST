package hu.karlricsi.entities;

import java.sql.Date;

public class Order {

	private int orderId;
	private int user_id;
	private Date date;
	private boolean closed;

	public Order() {
	}

	public Order(int orderId, int user_id, Date date, boolean closed) {
		super();
		this.orderId = orderId;
		this.user_id = user_id;
		this.date = date;
		this.closed = closed;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}