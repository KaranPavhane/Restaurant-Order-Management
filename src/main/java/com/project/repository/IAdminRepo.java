package com.project.repository;

import com.project.model.AdminModel;

public interface IAdminRepo {
	public boolean isAddNewAdmin(AdminModel admin);
//	public boolean deleteAdminByUserName(String username);
	
	public boolean loginAdmin(String username,  String password);
	
	public boolean verifyAdminDOB(String username, String dob);
	
	public boolean updateAdminPassword(String username, String newPassword);
	
}
