package com.juan.estevez.app.model;

public class OrderDetail {

	private Integer id;
	private String name;
	private double cant;
	private double price;
	private double total;

	public OrderDetail() {
	}

	public OrderDetail(Integer id, String name, double cant, double price, double total) {
		super();
		this.id = id;
		this.name = name;
		this.cant = cant;
		this.price = price;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCant() {
		return cant;
	}

	public void setCant(double cant) {
		this.cant = cant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", name=" + name + ", cant=" + cant + ", price=" + price + ", total=" + total
				+ "]";
	}

}
