package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Theme;

public class ThemeDao implements Dao<Theme> {

	@Override
	public void create(Theme obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_themes (name) "
					+ "VALUES ('"+ obj.getName() + "' );";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Theme read(int id) {
		try(Statement statement = connection.createStatement()){
		String str = "SELECT * FROM t_themes WHERE idTheme =" + id + ";";
		statement.execute(str);
		try (ResultSet rs = statement.getResultSet()){
			
			rs.next();
			int idTheme = rs.getInt(1);
			String name = rs.getString(2);
			
			Theme theme = new Theme(idTheme,name);
			
			return theme;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public boolean update(Theme obj) {
		
		String str = "UPDATE t_themes "
				+ "SET idTheme = " +obj.getIdTheme()+ " , name = '" + obj.getName() + " ' "
				+ "WHERE idTheme = "+ obj.getIdTheme() + " ;";
		
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
				+ "FROM t_themes "
				+ "WHERE idTheme = " + id + " ;";
		
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Theme> readAll() {
		ArrayList<Theme> allThemes = new ArrayList<Theme>();
		
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM t_themes;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				while(rs.next()) {
					allThemes.add(read(rs.getInt(1)));
				}
				
				return allThemes;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Theme> readBookByTheme(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Theme> readOrderByClient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
