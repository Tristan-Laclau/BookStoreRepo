package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface Dao<T> {
	
	public Connection connection = DbConnect.getConnection();
	public void create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(int id);
	public ArrayList<T> readAll();
	public ArrayList<T> readBookByTheme(int id);
	public ArrayList<T> readOrderByClient(int id);
	
}