package model.dao;

import db.DB;
import model.dao.impl.AdminDaoJDBC;

public class DaoFactory {

	public static AdminDao createAdminDao() {
		return new AdminDaoJDBC(DB.getConnection());
	}
	
}
