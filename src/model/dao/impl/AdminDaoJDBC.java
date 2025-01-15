package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.AdminDao;
import model.entities.Admin;

public class AdminDaoJDBC implements AdminDao {

	private Connection conn;

	public AdminDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Admin admin) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO admins_cadastrados " + "(login, senha) "
					+ "VALUES " + "(?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, admin.getLogin());
			st.setString(2, admin.getSenha());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("\nCadastro realizado com sucesso !!");
			} else {
				throw new DbException("\nErro inesperado !! Cadastro não realizado. Tente novamente");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Admin> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM admins_cadastrados");
			rs = st.executeQuery();
			List<Admin> adminsList = new ArrayList<>();
			while (rs.next()) {
				adminsList.add(instanciarAdmin(rs));
			}
		return adminsList;	
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	private Admin instanciarAdmin(ResultSet rs) throws SQLException {
		Admin obj = new Admin();
		obj.setLogin(rs.getString("login"));
		obj.setSenha(rs.getString("senha"));
		return obj;
	}

}
