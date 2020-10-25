package hu.karlricsi.feast.entities;

public class ProductConsumption {

	private String name;
	private double consumption;

	public ProductConsumption(String name, double consumption) {
		super();
		this.name = name;
		this.consumption = consumption;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

}