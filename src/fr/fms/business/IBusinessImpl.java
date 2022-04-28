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
	public boolean addToCart(Book book) {

		Book newBook = cart.get(book.getIdBook());
		if(newBook != null) {
			newBook.setQuantity(newBook.getQuantity()+1);
		}
		else cart.put(book.getIdBook(), book);
		
		return true;
	}

	@Override
	public boolean removeFromCart(Book book) {		
		if (!cart.containsKey(book.getIdBook())) {
			return false;
		}else {
			Book newBook = cart.get(book.getIdBook());
			if(newBook != null) {
				if(newBook.getQuantity() == 1) cart.remove(newBook.getIdBook());
				else {
					newBook.setQuantity(newBook.getQuantity()-1);
					cart.put(newBook.getIdBook(), newBook);
				}
			}
			return true;
		}

	}

	@Override
	public ArrayList<Book> getCart() {
		return new ArrayList<Book> (cart.values());
	}

	@Override
	public void checkout(int idClient) {
		if(clientDao.read(idClient) != null) {
			Order order = new Order(idClient,getTotal());
			System.out.println(getCart());
			order.setBookList(getCart());
			orderDao.create(order);
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
		return bookDao.readBookByTheme(idTheme);
	}

	public float getTotal() {
		float [] total = {0};
		cart.values().forEach((b) -> total[0] += b.getPrice() * b.getQuantity());
		return total[0];
	}

	public boolean isClient(String email, String password) {
		for (Client client : clientDao.readAll()) {
			if(client.getEmail().equalsIgnoreCase(email) && client.getPassword().equals(password)) return true;
		}
		return false;
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

	public void clearCart() {
		cart.clear();
	}

	public ArrayList<Order> readOrderByClient(int id){
		return orderDao.readOrderByClient(id);
	}
}
