package controller.admin;

import java.util.Scanner;

import model.dao.AdminDao;
import model.dao.DaoFactory;
import model.entities.Admin;

public class CadastrarAdmin {

	private Scanner sc;

	public CadastrarAdmin(Scanner sc) {
		this.sc = sc;
	}
	
	public void cadastrarAdmin() {
		AdminDao adminDao = DaoFactory.createAdminDao();
		System.out.println("\nCadastrar novo Administrador.");
		System.out.print("\nLogin: ");
		String login = sc.next();
		System.out.print("Senha: ");
		String senha = sc.next();
		Admin admin = new Admin(login, senha);
		adminDao.insert(admin);
	}
	
	
}
