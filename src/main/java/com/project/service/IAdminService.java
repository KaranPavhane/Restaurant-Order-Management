package com.project.service;

import com.project.model.AdminModel;

public interface IAdminService {
	public boolean isAddNewAdmin(AdminModel admin);

	public boolean loginAdmin(String username, String password);

	public boolean verifyAdminDOB(String username, String dob);

	public boolean updateAdminPassword(String username, String newPassword);
}
