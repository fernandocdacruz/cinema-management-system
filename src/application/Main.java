package application;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import Factory.AdminFactory;
import Utilities.GerenciadorDeMensagens;
import Utilities.ValidarMenuOp;
import controller.admin.AdminInterface;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println(GerenciadorDeMensagens.BEM_VINDO);
			int menuOp;
			do {
				mostrarMenu();
				ValidarMenuOp validarMenuOp = new ValidarMenuOp(sc);
				menuOp = validarMenuOp.obterOp(0, 2);
				executarOp(menuOp, sc, validarMenuOp);
			} while (menuOp != 0);
			System.out.println(GerenciadorDeMensagens.PROGRAMA_ENCERRADO);
		}

	}

	public static void mostrarMenu() {
		System.out.println("\n[0] - Sair");
		System.out.println("[1] - Área administrador");
		System.out.println("[2] - Área usuário");
	}
	
	public static void executarOp(int op, Scanner sc, ValidarMenuOp validarMenuOp) {
		switch (op) {
		case 1: AdminInterface admin = AdminFactory.createAdminLogin(sc, validarMenuOp);
				admin.loginInterface();
			break;
		}
	}

}
