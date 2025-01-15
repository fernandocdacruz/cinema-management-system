package model.entities;

public class Admin {
	
	private String login;
	private String senha;
	
	public Admin() {
	}

	public Admin(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
}
