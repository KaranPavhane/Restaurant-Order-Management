package com.project.service;

import java.util.List;

import com.project.model.StaffModel;
import com.project.repository.IStaffRepository;
import com.project.repository.StaffRepositoryImpl;

public class StaffServiceImpl implements IStaffService{

	IStaffRepository staffRepo = new StaffRepositoryImpl();
	
	@Override
	public boolean addNewStaff(StaffModel staffModel) {
		
		return staffRepo.addNewStaff(staffModel);
	}

	@Override
	public List<StaffModel> showAllStaffInRestaurent() {
		
		return staffRepo.showAllStaffInRestaurent();
	}

	@Override
	public boolean deleteStaffByName(String name) {
		
		return staffRepo.deleteStaffByName(name);
	}

	@Override
	public boolean updateStaffByName(String newStaffName, String staffEmail, String staffContact, int salary,
			String oldStaffName) {
		
		return staffRepo.updateStaffByName(newStaffName, staffEmail, staffContact, salary, oldStaffName);
	}

}
