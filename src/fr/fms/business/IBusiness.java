package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.Theme;

public interface IBusiness {
	
	/**
	 * Adds a book to the cart
	 * @param book
	 */
	
	public void addToCart(Book book);
	
	/**
	 * Removes a book from the cart
	 * @param book
	 */
	
	public void removeFromCart(Book book);
	
	/**
	 * Returns the content of the cart in an arraylist
	 * @return List of books in cart
	 */
	
	public ArrayList<Book> getCart();
	
	/**
	 * Creates a new order with the id of the client and the contents of its cart
	 * @param idClient the id of the user
	 */
	
	public void checkout(int idClient);
	
	/**
	 * Returns all books in the database.
	 * @return arraylist of books
	 */
	
	public ArrayList<Book> readAllBooks();
	
	/**
	 * Returns a book corresponding to the id in parameter
	 * @param id of the book
	 */
	
	public Book readBook(int id);
	
	/**
	 * Returns an arraylist with all themes in the database
	 * @return arraylist of themes
	 */
	
	public ArrayList<Theme> readAllThemes();
	
	/**
	 * Returns every book that has the specified theme
	 *@param the id of the selected theme
	 *@return arraylist of books
	 */
	
	public ArrayList<Book> readBookByTheme(int idTheme);
	
	/**
	 * Returns every order that belongs to the specified client
	 *@param the id of the selected client
	 *@return arraylist of orders
	 */
	
	public ArrayList<Order> readOrderByClient(int idClient);
}
