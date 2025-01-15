package controller.admin;

import java.util.Scanner;

import Utilities.GerenciadorDeMensagens;
import Utilities.ValidarMenuOp;

public class AdminInterface {

	private Scanner sc;
	private ValidarMenuOp validarMenuOp;

	public AdminInterface(Scanner sc, ValidarMenuOp validarMenuOp) {
		this.sc = sc;
		this.validarMenuOp = validarMenuOp;
	}

	public void loginInterface() {
		System.out.println("\nBem vindo a área dos Administradores.");
		int menuOp = 0;
		do {
			mostrarMenu();
			menuOp = validarMenuOp.obterOp(0, 2);
			executarOp(menuOp);
		} while (menuOp != 0);
		System.out.println("\n" + GerenciadorDeMensagens.BEM_VINDO);
	}
	
	public void mostrarMenu() {
		System.out.println("\n[0] - Sair");
		System.out.println("[1] - Fazer login");
		System.out.println("[2] - Cadastrar novo usuário");
	}
	
	public void executarOp(int menuOp) {
		switch (menuOp) {
		case 1: Login loginAdmin = new Login();
		loginAdmin.login();
			break;
		case 2: Cadastrar cadastrarAdmin = new Cadastrar(sc);
		cadastrarAdmin.cadastrarAdmin();
			break;
		}
	}
	
}
