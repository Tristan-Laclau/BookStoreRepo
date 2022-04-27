package fr.fms;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.BookDao;
import fr.fms.dao.ClientDao;
import fr.fms.dao.DbConnect;
import fr.fms.entities.Book;
import fr.fms.entities.Client;

public class BookStoreApp {

	private static Scanner scan = new Scanner(System.in);
	
	private static IBusinessImpl job = new IBusinessImpl();
	
	private static int idClient;
	

	public static boolean login() {
		
		System.out.println("Welcome to the authentification page");

		String email;
		String password;

		ArrayList<Client> clientList = new ArrayList<>();

		ClientDao clientDao = new ClientDao();

		System.out.print("Email :");

		email = scan.nextLine();	
		System.out.println();

		System.out.print("Password :");
		password = scan.nextLine();

		clientList = clientDao.readAll();

		for (Client c : clientList) {

			if (c.getEmail().equals(email) && c.getPassword().equals(password)){
				System.out.println("Successfully connected");
				idClient = c.getIdClient();
				return true;
			}
		}
		return false;
	}
	
	public static void checkout() {
		
		int input = 0;

		if(!job.isCartEmpty()) {

			System.out.println("Your cart : ");
			
			for (Book b : job.getCart()) System.out.println(b + " x " + b.getQuantity());

			System.out.println("Price : " + job.getTotal());

			System.out.println("Press 1 to confirm ; 2 to go back to menu");

			while(!scan.hasNextInt())scan.next();
			input = scan.nextInt();

			switch(input) {
			case 1:
				System.out.println("Thank you for your purchase");
				job.checkout(idClient);
				job.clearCart();
				break;
			case 2:
				System.out.println("Returning to menu");
				break;
			default:
				System.out.println("Wrong input, returning to menu");
				break;
			}
		}else {
			System.out.println("Cart empty, returning to menu");
		}
	}
	
	public static void createAccount() {
		
		System.out.println("Account does not exist, cannot create account yet, please contact your admin");
		
	}

	public static void displayMenu() {

		int input;

		System.out.println("What would you like to do");

		System.out.println("1 : View books available for purchase");
		System.out.println("2 : Add a book to cart");
		System.out.println("3 : Remove a book from cart");
		System.out.println("4 : Display cart");
		System.out.println("5 : Place an order");
		System.out.println("6 : Leave");

		while(!scan.hasNextInt())scan.next();

		input = scan.nextInt();

		switch(input) {
		case 1:
			
			for (Book b : job.readAllBooks()) {

				System.out.println(b);	
			}
			displayMenu();
			break;

		case 2:
			
			Book bookToAdd;

			System.out.println("Enter the ID of the desired book");

			for (Book b : job.readAllBooks()) {
				System.out.println( b.getIdBook() + " : " + b);
			}

			while(!scan.hasNextInt())scan.next();
			input = scan.nextInt();

			try {
				BookDao bookDao = new BookDao();
				bookToAdd = bookDao.read(input);
				bookToAdd.getIdBook(); //Si article est null, renvoie une exception ce qui nous fait passer dans le catch
				job.addToCart(bookToAdd);
				System.out.println("Book added to cart");
			}
			catch (Exception e) {
				System.out.println("This ID does not belong to any book, please try again");
			}
			displayMenu();
			break;
			
		case 3:
			
			Book bookToRemove;

			System.out.println("Enter the ID of the desired book");

			for (Book b : job.getCart()) {
				System.out.println( b.getIdBook() + " : " + b);
			}

			while(!scan.hasNextInt())scan.next();
			input = scan.nextInt();

			try {
				BookDao bookDao = new BookDao();
				bookToRemove = bookDao.read(input);
				job.getCart().get(bookToRemove.getIdBook()); //Si article est null, renvoie une exception ce qui nous fait passer dans le catch
				job.removeFromCart(bookToRemove);
				System.out.println("Book removed from cart");
			}
			catch (Exception e) {
				System.out.println("This ID does not belong to any book, please try again");
			}
			displayMenu();
			break;
			
		case 4:
			
			for (Book b : job.getCart()) {
				System.out.println(b + " x " + b.getQuantity());
			}
			displayMenu();
			break;
			
		case 5:
			
			checkout();
			displayMenu();
			
			break;
			
		case 6:
			
			System.out.println("Thank you for visiting our shop, see you soon!");
			
			break;

		default:
			System.out.println("Wrong input");
			displayMenu();
			break;
		}


	}

	public static void main(String[] args) {


		try (Statement statement = DbConnect.getConnection().createStatement()) {


			if (login()) {
				displayMenu();
			}else {
				createAccount();
				//displayMenu();
			}



		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}