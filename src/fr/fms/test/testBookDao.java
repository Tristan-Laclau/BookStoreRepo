package fr.fms.test;

import java.sql.Statement;

import fr.fms.dao.BookDao;
import fr.fms.dao.DbConnect;
import fr.fms.entities.Book;

public class testBookDao {

	public static void main(String[] args) {

		try(Statement statement = DbConnect.getConnection().createStatement()){

			BookDao bookDao = new BookDao();
			
			Book newBook = new Book("Test","TestAuthor","Desc",40);

			bookDao.create(newBook);
			
			newBook = bookDao.readAll().get(bookDao.readAll().size()-1);
			
			System.out.println("After insertion");
			
			for (Book b :bookDao.readAll()) {
				System.out.println(b);
			}
			
			System.out.println();
			
			newBook.setAuthor("Updated Author");
			bookDao.update(newBook);
			System.out.println("After update : "+ bookDao.read(newBook.getIdBook()));
			
			System.out.println();
			
			bookDao.delete(newBook.getIdBook());
			
			System.out.println("After deletion");
			
			for (Book b :bookDao.readAll()) {
				System.out.println(b);
			}
			
			System.out.println("On affiche les livres du thème 1");
			
			System.out.println(bookDao.readBookByTheme(1));


		}catch(Exception e) {
			e.printStackTrace();
		}


	}
}