package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.entities.Book;
import fr.fms.entities.Client;
import fr.fms.entities.Order;
import fr.fms.entities.Theme;

public class IBusinessImpl implements IBusiness{
	
	private HashMap<Integer,Book> cart = new HashMap<Integer,Book>();
	private Dao<Book> bookDao = DaoFactory.getBookDao();
	private Dao<Client> clientDao = DaoFactory.getClientDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<Theme> themeDao = DaoFactory.getThemeDao();

	@Override
	public void addToCart(Book book) {
		
		Book newBook = cart.get(book.getIdBook());
		if(newBook != null) {
			newBook.setQuantity(newBook.getQuantity()+1);
		}
		else cart.put(book.getIdBook(), book);
	}

	@Override
	public void removeFromCart(Book book) {
		Book newBook = cart.get(book.getIdBook());
		if(newBook != null) {
			if(newBook.getQuantity() == 1) cart.remove(newBook.getIdBook());
			else {
				newBook.setQuantity(newBook.getQuantity()-1);
				cart.put(newBook.getIdBook(), newBook);
			}
		}
		
	}

	@Override
	public ArrayList<Book> getCart() {
		return new ArrayList<Book> (cart.values());
	}

	@Override
	public void checkout(int idClient) {
		if(clientDao.read(idClient) != null) {
			Order order = new Order(idClient);
			orderDao.create(order);
				for(Book book : cart.values()) {
					//créer relation en base???
				}
			}
		}

	@Override
	public ArrayList<Book> readAllBooks() {
		return bookDao.readAll();
	}

	@Override
	public Book readBook(int id) {
		return bookDao.read(id);
	}

	@Override
	public ArrayList<Theme> readAllThemes() {
		return themeDao.readAll();
	}

	@Override
	public ArrayList<Book> readBookByTheme(int idTheme) {
		return bookDao.readByTheme(idTheme);
	}

}
