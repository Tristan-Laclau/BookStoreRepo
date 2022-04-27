package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Book;

public class BookDao implements Dao<Book>{

	@Override
	public void create(Book obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_books (title, author, description, price) "
					+ "VALUES ('" +obj.getTitle()+ "' ,'" + obj.getAuthor() + "' , '" + obj.getDescription() + "' , " + obj.getPrice() + " );";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Book read(int id) {
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_books WHERE idBook =" + id + ";";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				rs.next();
				int rsIdBook = rs.getInt(1);
				String rsTitle = rs.getString(2);
				String rsAuthor = rs.getString(3);
				String rsDescription = rs.getString(4);
				float rsPrice = rs.getFloat(5);
				
				Book book = new Book(rsIdBook, rsTitle, rsAuthor, rsDescription, rsPrice);
				
				return book;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
}


	@Override
	public boolean update(Book obj) {
		String str = "UPDATE t_books "
				+ "SET title = '"+ obj.getTitle()+"' , author = '"+obj.getAuthor() +"' , description = '"+ obj.getDescription() +"' , price = "+ obj.getPrice() + " "
				+ "WHERE idBook = "+ obj.getIdBook() + " ;";
		
		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(int id) {
		String str = "DELETE "
				+ "FROM book_details "
				+ "WHERE idBook = " + id + " ;";
		
		try(Statement statement = connection.createStatement()){
			statement.execute(str);

			str = "DELETE "
				+ "FROM t_books "
				+ "WHERE idBook = " + id + " ;";
		
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Book> readAll() {
		
		ArrayList<Book> allBooks = new ArrayList<Book>();
		
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_books;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				while(rs.next()) {
					allBooks.add(read(rs.getInt(1)));
				}
				
				return allBooks;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
