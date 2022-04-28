package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Book;
import fr.fms.entities.Order;

public class OrderDao implements Dao<Order> {

	@Override
	public void create(Order obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_orders (idClient, price) "
					+ "VALUES (" + obj.getIdClient() + " , " + obj.getTotalPrice() + " );";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
			
			obj.setIdOrder(readAll().get(readAll().size()-1).getIdOrder());
			
			System.out.println(obj.getBookList());
			
			if(obj.getBookList() != null) {
				
				for (int i = 0 ; i < obj.getBookList().size() ; i++) {

					str = "INSERT INTO order_details (IdOrder,IdBook,amount) VALUES ("+ obj.getIdOrder()+" , " +obj.getBookList().get(i).getIdBook()+" , "+ obj.getBookList().get(i).getQuantity()+ ") ;";
					statement.execute(str);	
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Order read(int id) {
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_orders WHERE idOrder =" + id + ";";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){

				rs.next();
				int idOrder = rs.getInt(1);
				int idClient = rs.getInt(2);
				float totalPrice = rs.getFloat(3);

				Order order = new Order(idOrder,idClient,totalPrice);

				return order;

			}catch (Exception e) {
				e.printStackTrace();
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Order obj) {

		String str = "UPDATE t_orders "
				+ "SET idOrder = '"+ obj.getIdOrder()+"' , idClient = '"+obj.getIdClient() +"' , price = "+ obj.getTotalPrice() + " "
				+ "WHERE idOrder = "+ obj.getIdOrder() + " ;";

		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int id) {

		try(Statement statement = connection.createStatement()){

			String str = "DELETE "
					+ "FROM t_orders "
					+ "WHERE idOrder = " + id + " ;";

			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> allOrders = new ArrayList<Order>();

		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_orders;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){

				while(rs.next()) {
					allOrders.add(read(rs.getInt(1)));
				}

				return allOrders;

			}catch (Exception e) {
				e.printStackTrace();
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Order> readOrderByClient(int id) {
		ArrayList<Order> result = new ArrayList<Order>();
		ArrayList<Book> bookList = new ArrayList<Book>();
		BookDao bookDao = new BookDao();
		
		String str = "SELECT * FROM t_orders WHERE t_orders.idClient = " + id + " ;";
		try(Statement statement = connection.createStatement()){
			statement.execute(str);
			try(ResultSet rs = statement.getResultSet()){
				while (rs.next()) {
					Order order = new Order(read(rs.getInt(1)));
				
		String bookRequest = "SELECT * FROM t_books JOIN order_details ON t_books.idBook = order_details.idBook WHERE order_details.idOrder = "+ rs.getInt(1) + " ;";
		Statement statementBookRequest = connection.createStatement();
		statementBookRequest.execute(bookRequest);
		
		ResultSet resultSet = statementBookRequest.getResultSet();
			while(resultSet.next()) {
				bookList.add(bookDao.read(resultSet.getInt(1)));
			}
			order.setBookList(bookList);
			result.add(order);
		}
				
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Order> readBookByTheme(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
