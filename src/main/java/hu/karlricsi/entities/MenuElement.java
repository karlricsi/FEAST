package hu.karlricsi.entities;

public class MenuElement {

	private int id;
	private MenuCategory category;
	private String name;
	private double price;

	public MenuElement(int id, MenuCategory category, String name, double price) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MenuCategory getCategory() {
		return category;
	}

	public void setCategory(MenuCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
