package org.learn.data;

//import java.util.List;

public class User {
	private int id;
	private String email;
	private String password;
	//private List<Test> tests;
	
	public User() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
//	public List<Test> getTests() {
//		return tests;
//	}
//	public void setTests(List<Test> tests) {
//		this.tests = tests;
//	}
}
