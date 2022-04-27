package fr.fms.entities;

public class Client {
	
	private int idClient;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	
	public Client(int idClient, String firstName, String lastName, String email, String password, String address) {
		this.idClient = idClient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
	}
	public Client(String firstName, String lastName, String email, String password, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int id) {
		this.idClient = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "Client : " +getFirstName() + " " + getLastName() + " " + getEmail() + " " + getAddress();
	}

}
