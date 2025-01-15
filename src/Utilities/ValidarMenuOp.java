package Utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidarMenuOp {

	private Scanner sc;
	
	public ValidarMenuOp(Scanner sc) {
		this.sc = sc;
	}

	public int obterOp(int opMin, int opMax) {
		int op = 0;
		boolean inputValido = false;
		while (!inputValido) {
			try {

				System.out.print("\nEscolha a opção desejada: ");
				op = sc.nextInt();
				testarOp(op, opMin, opMax);
				inputValido = true;

			} catch (InputMismatchException e) {
				System.out.println(GerenciadorDeMensagens.INPUT_INVALIDO);
				sc.next();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return op;
	}

	public void testarOp(int op, int opMin, int opMax) {
		if (op < opMin || op > opMax) {
			throw new IllegalArgumentException(GerenciadorDeMensagens.OPCAO_INVALIDA);
		}
	}
	
}
