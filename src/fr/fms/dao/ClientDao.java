package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Client;

public class ClientDao implements Dao<Client>{

	@Override
	public void create(Client obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_clients (firstname, lastname, email, password, address) "
					+ "VALUES ('" +obj.getFirstName()+ "' ,'" + obj.getLastName() + "' , '" + obj.getEmail() + "' , '" + obj.getPassword() + "' , '"+ obj.getAddress()+"'  );";
			statement.executeUpdate(str);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Client read(int id) {
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_clients WHERE idClient =" + id + ";";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				rs.next();
				int idClient = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				String address = rs.getString(6);
				
				Client client = new Client(idClient, firstName, lastName, email, password, address);
				
				return client;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Client obj) {
		String str = "UPDATE t_clients "
				+ "SET firstname = '"+ obj.getFirstName()+"' , lastname = '"+obj.getLastName() +"' , email = '"+ obj.getEmail() +"' , password = '"+ obj.getPassword() + "' , address = '"+obj.getAddress()+"' "
				+ "WHERE idClient = "+ obj.getIdClient() + " ;";
		
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
				+ "FROM t_orders "
				+ "WHERE IdClient = " + id + " ;";
		
		try(Statement statement = connection.createStatement()){
			statement.execute(str);

			str = "DELETE "
				+ "FROM t_clients "
				+ "WHERE IdClient = " + id + " ;";
		
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Client> readAll() {
		ArrayList<Client> allClients = new ArrayList<Client>();
		
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_clients;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				while(rs.next()) {
					allClients.add(read(rs.getInt(1)));
				}
				
				return allClients;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Client> readBookByTheme(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> readOrderByClient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
