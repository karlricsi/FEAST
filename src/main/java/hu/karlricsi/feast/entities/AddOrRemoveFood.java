package hu.karlricsi.feast.entities;

public class AddOrRemoveFood {

	private int userId;
	private int foodId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

}