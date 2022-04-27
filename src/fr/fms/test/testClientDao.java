package fr.fms.test;

import java.sql.Statement;
import fr.fms.dao.ClientDao;
import fr.fms.dao.DbConnect;
import fr.fms.entities.Client;

public class testClientDao {

	public static void main(String[] args) {

		try(Statement statement = DbConnect.getConnection().createStatement()){

			ClientDao clientDao = new ClientDao();
			
			Client newClient = new Client("Mathieu","Fix","mat@email.com","mfix","St Geours");

			clientDao.create(newClient);
			
			newClient = clientDao.readAll().get(clientDao.readAll().size()-1);
			
			System.out.println("After insertion");
			
			for (Client c :clientDao.readAll()) {
				System.out.println(c);
			}
			
			System.out.println();
			
			newClient.setEmail("updated@mail.com");
			clientDao.update(newClient);
			System.out.println("After update : "+ clientDao.read(newClient.getIdClient()));
			
			System.out.println();
			
			clientDao.delete(newClient.getIdClient());
			
			System.out.println("After deletion");
			
			for (Client c : clientDao.readAll()) {
				System.out.println(c);
			}


		}catch(Exception e) {
			e.printStackTrace();
		}


	}


}
