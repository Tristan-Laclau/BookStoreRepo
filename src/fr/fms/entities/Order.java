package fr.fms.entities;

import java.util.ArrayList;

public class Order {
	
	private int idOrder;
	private int idClient;
	private float totalPrice = 0;
	private ArrayList<Book> bookList;
	
	public Order(int idOrder, int idClient, float totalPrice) {
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.totalPrice = totalPrice;
	}
	public Order(int idClient) {
		this.idClient = idClient;
	}
	public Order(int idClient, float totalPrice) {
		this.idClient = idClient;
		this.totalPrice = totalPrice;
	}
	public Order(Order order) {
		this.idOrder = order.idOrder;
		this.idClient = order.idClient;
		this.totalPrice = order.totalPrice;
		this.bookList = order.bookList;
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
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	public String toString() {
		return ""+getIdClient()+" "+getTotalPrice();
	}
}
