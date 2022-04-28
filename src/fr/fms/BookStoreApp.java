package fr.fms;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.BookDao;
import fr.fms.dao.ClientDao;
import fr.fms.dao.DbConnect;
import fr.fms.entities.Book;
import fr.fms.entities.Client;
import fr.fms.entities.Order;

public class BookStoreApp {

	private static Scanner scan = new Scanner(System.in);

	private static IBusinessImpl job = new IBusinessImpl();

	private static int idClient;

	private static String format;

	private static String email;
	private static String password;


	public static boolean login() {

		System.out.println("Welcome to the authentification page");

		ArrayList<Client> clientList = new ArrayList<>();

		ClientDao clientDao = new ClientDao();

		System.out.print("Email :");

		try {
			email = scan.nextLine();
			System.out.println();

			System.out.print("Password :");
			password = scan.nextLine();
		} catch (Exception e) {
			System.out.println("An error has occured, please try again");
			return login();
		}

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

				format  = "%1$-50s | %2$-20s | %3$-10s | %4$-10s |\n";
				System.out.format(format, "Title", "Author","Price", "Quantity");
				System.out.println(String.join("", Collections.nCopies(100, "-")));
				for (Book b : job.getCart()) System.out.format(format, b.getTitle(),b.getAuthor(),b.getPrice(),b.getQuantity());	

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

	public static boolean createAccount() {

		System.out.println("Account does not exist, please enter your informations to proceed, or enter \"bye\" to leave");

		System.out.print("First name :");

		try {
			String firstName = scan.nextLine();	
			System.out.println();

			if(firstName.equalsIgnoreCase("bye")) return false;

			System.out.print("Last name :");
			String lastName = scan.nextLine();

			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher;

			do {
				System.out.print("Email :");
				email = scan.nextLine();
				matcher = pattern.matcher(email);
			} while (!matcher.matches());

			System.out.print("Password :");
			password = scan.nextLine();

			System.out.print("Address :");
			String address = scan.nextLine();

			ClientDao clientDao = new ClientDao();
			clientDao.create(new Client(firstName,lastName,email,password,address));
		} catch(Exception e) {
			System.out.println("An error has occured, please try again");
			return createAccount();
		}
		return true;

	}

	public static void displayMenu() {

		int input;

		System.out.println("What would you like to do");

		System.out.println("1 : View books available for purchase");
		System.out.println("2 : Add a book to cart");
		System.out.println("3 : Remove a book from cart");
		System.out.println("4 : Display cart");
		System.out.println("5 : Place an order");
		System.out.println("6 : Viewing previously placed order"); //For educational purposes
		System.out.println("7 : Leave");

		while(!scan.hasNextInt())scan.next();

		input = scan.nextInt();

		switch(input) {
		case 1:
			format  = "%1$-50s | %2$-20s | %3$-10s |\n";
			System.out.format(format, "Title", "Author","Price");
			System.out.println(String.join("", Collections.nCopies(90, "-")));
			for (Book b : job.readAllBooks()) {
				System.out.format(format, b.getTitle(),b.getAuthor(),b.getPrice());	
			}
			System.out.println();
			displayMenu();
			break;

		case 2:

			Book bookToAdd;

			System.out.println("Enter the ID of the desired book");

			format = "%1$-5s | %2$-50s | %3$-20s | %4$-10s |\n";
			System.out.format(format, "ID", "Title","Author","Price");
			System.out.println(String.join("", Collections.nCopies(100, "-")));
			for (Book b : job.readAllBooks()) {
				System.out.format(format, b.getIdBook() , b.getTitle() , b.getAuthor(), b.getPrice());
			}

			while(!scan.hasNextInt())scan.next();
			input = scan.nextInt();

			try {
				BookDao bookDao = new BookDao();
				bookToAdd = bookDao.read(input);
				bookToAdd.getIdBook();
				job.addToCart(bookToAdd);
				System.out.println("Book added to cart");
			}
			catch (Exception e) {
				System.out.println("This ID does not belong to any book, please try again");
			}
			System.out.println();
			displayMenu();
			break;

		case 3:


			if(!job.isCartEmpty()) {
				Book bookToRemove;

				System.out.println("Enter the ID of the desired book");

				format = "%1$-5s | %2$-50s | %3$-20s | %4$-10s | %5$-10s |\n";
				System.out.format(format, "ID", "Title","Author","Price","Quantity");
				System.out.println(String.join("", Collections.nCopies(100, "-")));
				for (Book b : job.getCart()) {
					System.out.format(format, b.getIdBook() , b.getTitle() , b.getAuthor(), b.getPrice(),b.getQuantity());
				}

				while(!scan.hasNextInt())scan.next();
				input = scan.nextInt();

				try {
					BookDao bookDao = new BookDao();
					bookToRemove = bookDao.read(input);
					if(job.removeFromCart(bookToRemove)) {
						System.out.println("Book removed from cart");
					}else {
						System.out.println("Unable to remove book from cart");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("Your cart is empty");
			}
			System.out.println();
			displayMenu();
			break;

		case 4:

			if(!job.isCartEmpty()) {
				format  = "%1$-50s | %2$-20s | %3$-10s | %4$-10s |\n";
				System.out.format(format, "Title", "Author","Price", "Quantity");
				System.out.println(String.join("", Collections.nCopies(100, "-")));
				for (Book b : job.getCart()) {
					System.out.format(format, b.getTitle(),b.getAuthor(),b.getPrice(),b.getQuantity());	
				}
			}else {
				System.out.println("Your cart is empty");
			}

			System.out.println();
			displayMenu();
			break;

		case 5:

			checkout();
			System.out.println();
			displayMenu();

			break;

		case 6:

			System.out.println("Here are all the orders you placed so far");
			for (Order o : job.readOrderByClient(idClient)) {
				System.out.println("Order #"+o.getIdOrder());
				format  = "%1$-50s | %2$-20s | %3$-10s |\n";
				System.out.format(format, "Title", "Author","Price");
				System.out.println(String.join("", Collections.nCopies(90, "-")));
				for(Book b : o.getBookList()) {
					System.out.format(format, b.getTitle(),b.getAuthor(),b.getPrice());	
				}
				System.out.println("Total price : "+ o.getTotalPrice());
				System.out.println(String.join("", Collections.nCopies(90, "-")));
			}
			System.out.println();
			displayMenu();

			break;

		case 7:

			System.out.println("Thank you for visiting our shop, see you soon!");

			break;

		default:
			System.out.println("Wrong input");
			System.out.println();
			displayMenu();
			break;
		}


	}

	public static void main(String[] args) {


		try (Statement statement = DbConnect.getConnection().createStatement()) {


			if (login()) {
				displayMenu();
			}else if (createAccount()){
				displayMenu();
			}else {
				System.out.println("Thanks for visiting our shop, see you soon!");
			}



		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}