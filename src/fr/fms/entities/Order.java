package fr.fms.entities;

public class Order {
	
	private int idOrder;
	private int idClient;
	private float totalPrice = 0;
	
	public Order(int idOrder, int idClient, float totalPrice) {
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.totalPrice = totalPrice;
	}
	public Order(int idClient) {
		this.idClient = idClient;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String toString() {
		return ""+getIdClient()+" "+getTotalPrice();
	}
}
