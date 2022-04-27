package fr.fms.entities;

public class Theme {
	
	private int idTheme;
	private String name;
	
	public Theme(int idTheme, String name) {
		this.idTheme = idTheme;
		this.name = name;
	}
	public Theme(String name) {
		this.name = name;
	}
	public int getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return getName();
	}
}
