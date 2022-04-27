package fr.fms.dao;

import fr.fms.entities.Book;
import fr.fms.entities.Client;
import fr.fms.entities.Order;
import fr.fms.entities.Theme;

public class DaoFactory {
	
	public static Dao<Book> getBookDao(){
		return new BookDao();
	}
	
	public static Dao<Client> getClientDao(){
		return new ClientDao();
	}
	
	public static Dao<Order> getOrderDao(){
		return new OrderDao();
	}
	
	public static Dao<Theme> getThemeDao(){
		return new ThemeDao();
	}

}
