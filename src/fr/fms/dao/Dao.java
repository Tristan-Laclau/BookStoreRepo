package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.entities.Book;

public interface Dao<T> {
	
	public Connection connection = DbConnect.getConnection();
	public void create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(int id);
	public ArrayList<T> readAll();
	public ArrayList<Book> readByTheme(int idTheme);
	
}