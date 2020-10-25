package hu.karlricsi.feast.entities;

public class BasketItem {

	private int foodId;
	private String foodName;
	private double price;
	private int quantity;

	public BasketItem(int foodId, String foodName, double price, int quantity) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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