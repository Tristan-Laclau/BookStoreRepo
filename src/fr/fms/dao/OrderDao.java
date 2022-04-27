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
			String str = "INSERT INTO t_orders (idOrder, idClient, price) "
					+ "VALUES (" +obj.getIdOrder()+ " ," + obj.getIdClient() + " , " + obj.getTotalPrice() + " );";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
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
	public ArrayList<Book> readByTheme(int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

}
