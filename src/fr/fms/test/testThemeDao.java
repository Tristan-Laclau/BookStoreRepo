package fr.fms.test;

import java.sql.Statement;

import fr.fms.dao.DbConnect;
import fr.fms.dao.ThemeDao;
import fr.fms.entities.Theme;

public class testThemeDao {
	
	public static void main(String[] args) {

		try(Statement statement = DbConnect.getConnection().createStatement()){

			ThemeDao themeDao = new ThemeDao();

			Theme newTheme = new Theme("Horreur");

			themeDao.create(newTheme);

			newTheme = themeDao.readAll().get(themeDao.readAll().size()-1);

			System.out.println("After insertion");

			for (Theme t :themeDao.readAll()) {
				System.out.println(t);
			}

			System.out.println();

			newTheme.setName("Horror");
			themeDao.update(newTheme);
			
			System.out.println("After update : "+ themeDao.read(newTheme.getIdTheme()));

			System.out.println();

			themeDao.delete(newTheme.getIdTheme());

			System.out.println("After deletion");

			for (Theme t : themeDao.readAll()) {
				System.out.println(t);
			}


		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
