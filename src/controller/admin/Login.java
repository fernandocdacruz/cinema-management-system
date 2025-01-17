package controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Utilities.GerenciadorDeMensagens;
import model.dao.AdminDao;
import model.dao.DaoFactory;
import model.entities.Admin;

public class Login {

	private Scanner sc;

	public Login(Scanner sc) {
		this.sc = sc;
	}

	public void login() {

		AdminDao adminDao = DaoFactory.createAdminDao();

		List<Admin> adminsList = adminDao.findAll();

		if (adminsList.isEmpty()) {
			System.out.println(GerenciadorDeMensagens.LISTA_ADMINS_VAZIA);
		} else {
			boolean inputValido = false;
			boolean senhaIncorreta = false;
			while (!inputValido) {
				try {
					String login = obterString(GerenciadorDeMensagens.LOGIN_PROMPT, senhaIncorreta);
					Optional<Admin> admin = adminsList.stream().filter(a -> a.getLogin().equals(login)).findFirst();
					if (admin.isEmpty()) {
						senhaIncorreta = true;
						System.out.println(GerenciadorDeMensagens.LOGIN_INVALIDO);
					} else {
						senhaIncorreta = true;
						String senha = obterString(GerenciadorDeMensagens.SENHA_PROMPT, senhaIncorreta);
						if (admin.get().getSenha().equals(senha)) {
							inputValido = true;
							System.out.print("\nSeja bem vindo!");
						} else {
							senhaIncorreta = true;
							throw new IllegalArgumentException(GerenciadorDeMensagens.SENHA_INCORRETA);
						}
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

		}
	}

	public String obterString(String prompt, boolean senha) {
		String string = "";
		boolean senhaIncorreta = senha;
		boolean inputValido = false;
		boolean stringVazia = false;
		System.out.print(prompt);
		if (!senhaIncorreta) {
			sc.nextLine();
		}

		while (!inputValido) {
			try {
				// ver pq esta comendo o input
				if (stringVazia == true) {
					System.out.print(prompt);
				}
				string = sc.nextLine();
				stringVazia = testarStringVazia(string);
				InputVazioExcpetion(stringVazia);
				inputValido = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return string;
	}

	public boolean testarStringVazia(String string) {
		boolean stringVazia = false;
		if (string == null || string.isEmpty()) {
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
