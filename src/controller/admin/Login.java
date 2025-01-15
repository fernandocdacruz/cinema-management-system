package controller.admin;

import java.util.ArrayList;
import java.util.List;

import Utilities.GerenciadorDeMensagens;
import model.dao.AdminDao;
import model.dao.DaoFactory;
import model.entities.Admin;

public class Login {

	public void login() {
		AdminDao adminDao = DaoFactory.createAdminDao();
		List<Admin> adminsList = adminDao.findAll();
		if (adminsList.isEmpty()) {
			System.out.println(GerenciadorDeMensagens.LISTA_ADMINS_VAZIA);
		} else {
			
		}
	}
	
}
