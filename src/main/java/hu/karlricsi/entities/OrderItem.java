package hu.karlricsi.entities;

public class OrderItem {

	private int orderId;
	private int foodId;
	private double price;
	private int quantity;

	public OrderItem(int orderId, int foodId) {
		super();
		this.orderId = orderId;
		this.foodId = foodId;
	}

	public OrderItem(int orderId, int foodId, double price, int quantity) {
		super();
		this.orderId = orderId;
		this.foodId = foodId;
		this.price = price;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int id) {
		this.foodId = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}