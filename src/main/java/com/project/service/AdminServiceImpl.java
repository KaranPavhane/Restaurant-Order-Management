package com.project.service;

import com.project.model.AdminModel;
import com.project.repository.AdminRepoImpl;
import com.project.repository.IAdminRepo;

public class AdminServiceImpl  implements IAdminService{
	IAdminRepo adminRepo=new AdminRepoImpl();
	
	@Override
	public boolean isAddNewAdmin(AdminModel admin) {
		return adminRepo.isAddNewAdmin(admin);
	}

	@Override
	public boolean loginAdmin(String username, String password) {
		return adminRepo.loginAdmin(username, password);
	}

	
}
