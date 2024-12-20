package com.project.service;

import com.project.model.AdminModel;

public interface IAdminService {
	public boolean isAddNewAdmin(AdminModel admin);
	
	public boolean loginAdmin(String username, String password);
}
