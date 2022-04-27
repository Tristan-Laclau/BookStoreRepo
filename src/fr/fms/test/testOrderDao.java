package fr.fms.test;

import java.sql.Statement;
import fr.fms.dao.DbConnect;
import fr.fms.dao.OrderDao;
import fr.fms.entities.Order;

public class testOrderDao {

	public static void main(String[] args) {

		try(Statement statement = DbConnect.getConnection().createStatement()){

			OrderDao orderDao = new OrderDao();

			Order newOrder = new Order(2);

			orderDao.create(newOrder);

			newOrder = orderDao.readAll().get(orderDao.readAll().size()-1);

			System.out.println("After insertion");

			for (Order o :orderDao.readAll()) {
				System.out.println(o);
			}

			System.out.println();

			newOrder.setTotalPrice(10);
			orderDao.update(newOrder);
			
			System.out.println("After update : "+ orderDao.read(newOrder.getIdOrder()));

			System.out.println();

			orderDao.delete(newOrder.getIdOrder());

			System.out.println("After deletion");

			for (Order o : orderDao.readAll()) {
				System.out.println(o);
			}


		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
