package model.dao;

import java.util.List;

import model.entities.Admin;

public interface AdminDao {
	void insert(Admin admin);
	List<Admin> findAll();
}
