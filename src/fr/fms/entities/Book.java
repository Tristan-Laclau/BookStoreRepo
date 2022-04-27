package fr.fms.entities;

public class Book {
	
	
	private int idBook;
	private String title;
	private String author;
	private String description;
	private float price;
	private boolean isUsed;
	private int quantity = 1;
	
	public Book(String title, String author, String description, float price) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.price = price;
	}
	public Book(int idBook, String title, String author, String description, float price) {
		this.idBook = idBook;
		this.title = title;
		this.author = author;
		this.description = description;
		this.price = price;
	}
	public Book(Book book) {
		this.idBook = book.getIdBook();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.description = book.getDescription();
		this.price = book.getPrice();
		this.isUsed = book.isUsed();
	}
	public int getIdBook() {
		return idBook;
	}
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return " Title : "+ this.getTitle()+" | Author : "+ this.getAuthor();
	}
	
}
