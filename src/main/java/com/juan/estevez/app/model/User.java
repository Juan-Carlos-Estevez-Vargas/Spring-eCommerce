package com.juan.estevez.app.model;

public class User {

	private Integer id;
	private String name;
	private String username;
	private String email;
	private String adredd;
	private String phone;
	private String type;
	private String password;

	public User() {
	}

	public User(Integer id, String name, String username, String email, String adredd, String phone, String type,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.adredd = adredd;
		this.phone = phone;
		this.type = type;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdredd() {
		return adredd;
	}

	public void setAdredd(String adredd) {
		this.adredd = adredd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", adredd="
				+ adredd + ", phone=" + phone + ", type=" + type + ", password=" + password + "]";
	}

}
