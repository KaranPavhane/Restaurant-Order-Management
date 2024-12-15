package com.project.service;

import com.project.model.AdminModel;
import com.project.repository.AdminRepo;
import com.project.repository.AdminRepoImpl;

public class AdminServiceImpl  implements AdminService{
	AdminRepo adminRepo=new AdminRepoImpl();
	
	@Override
	public boolean isAddNewAdmin(AdminModel admin) {
		System.out.println(admin.getAdmin_username());
		// TODO Auto-generated method stub
		return adminRepo.isAddNewAdmin(admin);
	}

}
