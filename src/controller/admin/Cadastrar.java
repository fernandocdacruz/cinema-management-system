package controller.admin;

import java.util.List;
import java.util.Scanner;

import Utilities.GerenciadorDeMensagens;
import model.dao.AdminDao;
import model.dao.DaoFactory;
import model.entities.Admin;

public class Cadastrar {

	private Scanner sc;

	public Cadastrar(Scanner sc) {
		this.sc = sc;
	}
	
	public void cadastrarAdmin() {
		AdminDao adminDao = DaoFactory.createAdminDao();
		System.out.println("\nCadastrar novo Administrador.");
		String login = loginCadastro(adminDao);
		System.out.print("Senha: ");
		String senha = sc.next();
		Admin admin = new Admin(login, senha);
		adminDao.insert(admin);
	}
	
	public String loginCadastro(AdminDao adminDao) {
		List<Admin> adminsList = adminDao.findAll();
		String login = "";
		boolean inputValido = false;
		while (!inputValido) {
			try {
				System.out.print("\nLogin: ");
				sc.nextLine();
				String testeLogin = sc.nextLine();
				testarStringVazia(testeLogin);
				if (adminsList.stream().anyMatch(ad -> ad.getLogin().equals(testeLogin))) {
					System.out.println(GerenciadorDeMensagens.LOGIN_EM_USO);
				} else {
					login = testeLogin;
					inputValido = true;
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			
		}
		return login;
	}
	
	public void testarStringVazia(String login) {
		if (login.isBlank()) {
			throw new IllegalArgumentException(GerenciadorDeMensagens.INPUT_VAZIO);
		}
	}
	
}
