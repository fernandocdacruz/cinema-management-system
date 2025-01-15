package Factory;

import java.util.Scanner;

import Utilities.ValidarMenuOp;
import controller.admin.AdminInterface;

public class AdminFactory {
	
	public static AdminInterface createAdminLogin(Scanner sc, ValidarMenuOp validarMenuOp) {
        return new AdminInterface(sc, validarMenuOp);
    }
}
