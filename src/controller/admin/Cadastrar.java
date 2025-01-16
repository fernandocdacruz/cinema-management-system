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
		String senha = senhaCadastro();
		Admin admin = new Admin(login, senha);
		adminDao.insert(admin);
	}

	public String loginCadastro(AdminDao adminDao) {
		List<Admin> adminsList = adminDao.findAll();
		String login = "";
		boolean inputValido = false;
		boolean stringVazia = false;
		System.out.print(GerenciadorDeMensagens.LOGIN_PROMPT);
		sc.nextLine();
		while (!inputValido) {
			try {
				if (stringVazia == true) {
					System.out.print(GerenciadorDeMensagens.LOGIN_PROMPT);
				}
				String testeLogin = sc.nextLine();
				stringVazia = testarStringVazia(testeLogin);
				InputVazioExcpetion(stringVazia);
				if (adminsList.stream().anyMatch(ad -> ad.getLogin().equals(testeLogin))) {
					System.out.println(GerenciadorDeMensagens.LOGIN_EM_USO);
					System.out.print(GerenciadorDeMensagens.LOGIN_PROMPT);
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
	
	public String senhaCadastro() {
		String senha = "";
		boolean inputValido = false;
		boolean stringVazia = false;
		System.out.print(GerenciadorDeMensagens.SENHA_PROMPT);
		while (!inputValido) {
			try {
				if (stringVazia == true) {
					System.out.print("\n" + GerenciadorDeMensagens.SENHA_PROMPT);
				}
				senha = sc.nextLine();
				stringVazia = testarStringVazia(senha);
				InputVazioExcpetion(stringVazia);
				inputValido = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return senha;
	}

	public boolean testarStringVazia(String login) {
		boolean stringVazia = false;
		if (login.isBlank()) {
			stringVazia = true;
		}
		return stringVazia;
	}
	
	public void InputVazioExcpetion(boolean stringVazia) {
		if (stringVazia) {
			throw new IllegalArgumentException(GerenciadorDeMensagens.INPUT_VAZIO);
		}
	}

}
